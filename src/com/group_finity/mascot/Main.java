package com.group_finity.mascot;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.group_finity.mascot.config.Configuration;
import com.group_finity.mascot.config.Entry;
import com.group_finity.mascot.exception.BehaviorInstantiationException;
import com.group_finity.mascot.exception.CantBeAliveException;
import com.group_finity.mascot.exception.ConfigurationException;

/**
 * Program entry point.
 *
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */
public class Main {

	private static final Logger log = Logger.getLogger(Main.class.getName());

	// Action that matches the "Gather!" context menu command
	static final String BEHAVIOR_GATHER = "GatherAroundMouse";

	static {
		
		try {
			LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static Main instance = new Main();

	public static Main getInstance() {
		return instance;
	}

	private final Manager manager = new Manager();

	private final Configuration configuration = new Configuration();

	public static void main(final String[] args) {
		getInstance().run();
	}

	public void run() {
		
		// Load settings
		loadConfiguration();

		// Create the tray icon
		createTrayIcon();

		// Create the first mascot
		createMascot();

		getManager().start ();
		
		getManager().start();
	}

	private void loadConfiguration() {

		try {
			log.log(Level.INFO, "Read Action File ((0))", "/actions.xml");

			final Document actions = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					Main.class.getResourceAsStream("/actions.xml"));

			this.getConfiguration().load(new Entry(actions.getDocumentElement()));

			log.log(Level.INFO, "Read Behavior File ((0))", "/behaviors.xml");			
			
			final Document behaviors = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					Main.class.getResourceAsStream ("/behaviors.xml"));

			this.getConfiguration().load(new Entry(behaviors.getDocumentElement()));

			this.getConfiguration().validate();
			
		} catch (final IOException e) {
			log.log (Level.SEVERE, "Failed to load configuration files", e);
			exit();
		} catch (final SAXException e) {
			log.log (Level.SEVERE, "Failed to load configuration files", e);
			exit();
		} catch (final ParserConfigurationException e) {
			log.log (Level.SEVERE, "Failed to load configuration files", e);
			exit();
		} catch (final ConfigurationException e) {
			log.log (Level.SEVERE, "Failed to load configuration files", e);
			exit();
		}
	}

	/**
	* Create a tray icon.
	* @ Throws AWTException
	* @ Throws IOException
	*/
	private void createTrayIcon() {

		log.log (Level.INFO, "create a tray icon");

		// "Make Another!" menu item
		final MenuItem increaseMenu = new MenuItem ("Another One!");
		increaseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				createMascot();
			}
		});

		// "Gather!" Menu item
		final MenuItem gatherMenu = new MenuItem ("Follow Mouse!");
		gatherMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				Main.this.getManager().setBehaviorAll(Main.this.getConfiguration(), BEHAVIOR_GATHER);
			}
		});

		// "Only One!" menu item
		final MenuItem oneMenu = new MenuItem("Reduce to One!");
		oneMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				Main.this.getManager().remainOne();
			}
		});

		// "Restore IE!" menu item
		final MenuItem restoreMenu = new MenuItem("Restore IE!");
		restoreMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				NativeFactory.getInstance().getEnvironment().restoreIE();
			}
		});

		// "Bye Bye!" menu item
		final MenuItem closeMenu = new MenuItem("Bye Everyone!");
		closeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		
		// Create the context "popup" menu.
		final PopupMenu trayPopup = new PopupMenu();
		trayPopup.add(increaseMenu);
		trayPopup.add(gatherMenu);
		trayPopup.add(oneMenu);
		trayPopup.add(restoreMenu);
		trayPopup.add(new MenuItem("-"));
		trayPopup.add(closeMenu);

		try {
			// Create the tray icon
			final TrayIcon icon = new TrayIcon(ImageIO.read(Main.class.getResource("/icon.png")), "shimeji-ee", trayPopup);
			icon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(final MouseEvent e) {
					// When the tray icon is left clicked:
					if (SwingUtilities.isLeftMouseButton(e)) {
						createMascot();
					}
				}
			});
			
			// Show tray icon
			SystemTray.getSystemTray().add(icon);

		} catch (final IOException e) {
			log.log(Level.SEVERE, "Failed to create tray icon", e);
			exit();

		} catch (final AWTException e) {
			log.log(Level.SEVERE, "Failed to create tray icon", e);
			Mascot.setShowSystemTrayMenu(true);
			getManager().setExitOnLastRemoved(true);
		}

	}

	/**
	 * Create a mascot
	 */
	public void createMascot() {
		log.log(Level.INFO, "create a mascot");

		// Create one mascot
		final Mascot mascot = new Mascot();

		// Create it outside the bounds of the screen
		mascot.setAnchor(new Point(-1000, -1000));
		
		// Randomize the initial orientation
		mascot.setLookRight(Math.random() < 0.5);

		try {
			mascot.setBehavior(getConfiguration().buildBehavior(null, mascot));
			this.getManager().add(mascot);
		} catch (final BehaviorInstantiationException e) {
			log.log (Level.SEVERE, "Failed to initialize the first action", e);
			mascot.dispose();
		} catch (final CantBeAliveException e) {
			log.log (Level.SEVERE, "Fatal Error", e);
			mascot.dispose();
		}
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}

	private Manager getManager() {
		return this.manager;
	}

	public void exit() {
		this.getManager().disposeAll();
		this.getManager().stop();

		System.exit(0);
	}

}
