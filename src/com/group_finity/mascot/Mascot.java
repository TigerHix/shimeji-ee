package com.group_finity.mascot;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.group_finity.mascot.behavior.Behavior;
import com.group_finity.mascot.environment.MascotEnvironment;
import com.group_finity.mascot.exception.CantBeAliveException;
import com.group_finity.mascot.image.MascotImage;
import com.group_finity.mascot.image.TranslucentWindow;

/**
 * Mascot object.
 *
 * The mascot represents the long-term, complex behavior and (@link Behavior),
 * Represents a short-term movements in the monotonous work with (@link Action).
 *
 * The mascot they have an internal timer, at a constant interval to call (@link Action).
 * (@link Action) is (@link #animate (Point, MascotImage, boolean)) method or by calling
 * To animate the mascot.
 *
 * (@link Action) or exits, the other at a certain time is called (@link Behavior), the next move to (@link Action).
 *
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */
public class Mascot {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Mascot.class.getName());

	private static AtomicInteger lastId = new AtomicInteger();

	private final int id;
	
	private String imageSet = "";

	/**
	 * A window that displays the mascot.
	 */
	private final TranslucentWindow window = NativeFactory.getInstance().newTransparentWindow();

	/**
	 * Managers are managing the mascot.
	 */
	private Manager manager = null;

	/**
	 * Mascot ground coordinates.
	 * Or feet, for example, when part of the hand is hanging.
	 */
	private Point anchor = new Point(0, 0);

	/**
	 * Image to display.
	 */
	private MascotImage image = null;

	/**
	 * Whether looking right or left.
	 * The original image is treated as left, true means picture must be inverted.
	 */
	private boolean lookRight = false;

	/**
	 * Object representing the long-term behavior.
	 */
	private Behavior behavior = null;

	/**
	 * Increases with each tick of the timer.
	 */
	private int time = 0;

	/**
	 * Whether the animation is running.
	 */
	private boolean animating = true;

	private MascotEnvironment environment = new MascotEnvironment(this);

	public Mascot( final String imageSet ) {
		this.id = lastId.incrementAndGet();
		this.imageSet = imageSet;

		log.log(Level.INFO, "Created a mascot ({0})", this);

		// Always show on top
		getWindow().asJWindow().setAlwaysOnTop(true);

		// Register the mouse handler
		getWindow().asJWindow().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				Mascot.this.mousePressed(e);
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				Mascot.this.mouseReleased(e);
			}
		});

	}

	@Override
	public String toString() {
		return "mascot" + this.id;
	}

	private void mousePressed(final MouseEvent event) {

		// Switch to drag the animation when the mouse is down
		if (getBehavior() != null) {
			try {
				getBehavior().mousePressed(event);
			} catch (final CantBeAliveException e) {
				log.log(Level.SEVERE, "The situation can not keep going", e);
				dispose();
			}
		}

	}

	private void mouseReleased(final MouseEvent event) {

		if (event.isPopupTrigger()) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					showPopup(event.getX(), event.getY());
				}
			});
		} else {
			if (getBehavior() != null) {
				try {
					getBehavior().mouseReleased(event);
				} catch (final CantBeAliveException e) {
					log.log(Level.SEVERE, "Fatal Error", e);
					dispose();
				}
			}
		}

	}

	private void showPopup(final int x, final int y) {
		final JPopupMenu popup = new JPopupMenu();

		popup.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(final PopupMenuEvent e) {
			}

			@Override
			public void popupMenuWillBecomeInvisible(final PopupMenuEvent e) {
				setAnimating(true);
			}

			@Override
			public void popupMenuWillBecomeVisible(final PopupMenuEvent e) {
				setAnimating(false);
			}
		});

		// "Make Another!" menu item
		final JMenuItem increaseMenu = new JMenuItem("Another One!");
		increaseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				Main.getInstance().createMascot(imageSet);
			}
		});

		final JMenuItem disposeMenu = new JMenuItem("Bye Bye!");
		disposeMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});		
		
		// "Gather!" Menu item
		final JMenuItem gatherMenu = new JMenuItem("Follow Mouse!");
		gatherMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				getManager().setBehaviorAll(Main.getInstance().getConfiguration(imageSet), Main.BEHAVIOR_GATHER, imageSet);
			}
		});

		// "Only One!" menu item
		final JMenuItem oneMenu = new JMenuItem("Reduce to One!");
		oneMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				getManager().remainOne(imageSet);
			}
		});

		// "Restore IE!" menu item
		final JMenuItem restoreMenu = new JMenuItem("Restore IE!");
		restoreMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				NativeFactory.getInstance().getEnvironment().restoreIE();
			}
		});

		// "Bye Bye!" menu item
		final JMenuItem closeMenu = new JMenuItem("Bye Everyone!");
		closeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Main.getInstance().exit();
			}
		});

		popup.add(increaseMenu);
		popup.add(disposeMenu);	
		popup.add(new JSeparator());		
		popup.add(gatherMenu);
		popup.add(oneMenu);
		popup.add(restoreMenu);
		popup.add(new JSeparator());
		popup.add(closeMenu);

		getWindow().asJWindow().requestFocus();

		popup.show(getWindow().asJWindow(), x, y);

	}

	void tick() {
		if (isAnimating()) {
			if (getBehavior() != null) {

				try {
					getBehavior().next();
				} catch (final CantBeAliveException e) {
					log.log(Level.SEVERE, "Fatal Error.", e);
					dispose();
				}

				setTime(getTime() + 1);
			}
		}
	}

	public void apply() {
		if (isAnimating()) {

			// Make sure there's an image
			if (getImage() != null) {

				// Set the window region
				getWindow().asJWindow().setBounds(getBounds());

				// Set Images
				getWindow().setImage(getImage().getImage());

				// Display
				if (!getWindow().asJWindow().isVisible()) {
					getWindow().asJWindow().setVisible(true);
				}

				// Redraw
				getWindow().updateImage();
			} else {
				if (getWindow().asJWindow().isVisible()) {
					getWindow().asJWindow().setVisible(false);
				}
			}
		}
	}

	public void dispose() {
		log.log(Level.INFO, "destroy mascot ({0})", this);

		getWindow().asJWindow().dispose();
		if (getManager() != null) {
			getManager().remove(Mascot.this);
		}
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	public Point getAnchor() {
		return this.anchor;
	}

	public void setAnchor(Point anchor) {
		this.anchor = anchor;
	}

	public MascotImage getImage() {
		return this.image;
	}

	public void setImage(final MascotImage image) {
		this.image = image;
	}

	public boolean isLookRight() {
		return this.lookRight;
	}

	public void setLookRight(final boolean lookRight) {
		this.lookRight = lookRight;
	}

	public Rectangle getBounds() {
		// Central area of the window find the image coordinates and ground coordinates.
		final int top = getAnchor().y - getImage().getCenter().y;
		final int left = getAnchor().x - getImage().getCenter().x;

		final Rectangle result = new Rectangle(left, top, getImage().getSize().width, getImage().getSize().height);

		return result;
	}

	public int getTime() {
		return this.time;
	}

	private void setTime(final int time) {
		this.time = time;
	}

	public Behavior getBehavior() {
		return this.behavior;
	}

	public void setBehavior(final Behavior behavior) throws CantBeAliveException {
		this.behavior = behavior;
		this.behavior.init(this);
	}

	public int getTotalCount() {
		return getManager().getCount();
	}

	private boolean isAnimating() {
		return this.animating;
	}

	private void setAnimating(final boolean animating) {
		this.animating = animating;
	}

	private TranslucentWindow getWindow() {
		return this.window;
	}

	public MascotEnvironment getEnvironment() {
		return environment;
	}
	
	public String getImageSet() {
		return imageSet;
	}	
}
