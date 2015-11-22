Converting existing Shimeji is a fairly easy process.  If the Shimeji uses the standard configuration file with 46 images, then all you need to do is rename the Shimeji's img folder (any name is fine) and copy it to the Shimeji-ee img folder.

# Converting Configuration Files #

If the configuration files have been modified, then they need to be converted for Shimeji-ee.  First, you will need to create a conf file under the folder you made in Shimeji-ee for the Shimeji you are converting.  You will then need to copy the files in the original Shimeji's conf folder to the new conf folder you just made.  Then you need to rename "動作.xml" to "actions.xml" and "行動.xml" to "behaviors.xml".  Finally, you need to translate all the important words of the files.

For the translation part, you'll need to do the following.  (If you don't have the "ConvertShimeji" python script or the "Python Script" plugin, read the next section first):

1. Open "actions.xml" in Notepad++

2. Go to "Plugins" on the menu bar, then "Python Script", then "Scripts", and finally click on "ConvertShimeji"

3. Wait until the script finishes and then save the file.  Most, if not all, of the text in the actions.xml file should be in English.

4. Repeat steps 1-3 for the "behaviors.xml" file.

# Setting up the the "Convert Shimeji" Script #

To set up the "ConvertShimeji" python script:

1. Download Notepad++: http://notepad-plus-plus.org/

2. Download the "Python Script" plugin.  To download and install the plugin:

> a. Open Notepad++

> b. Go to "Plugins" on the menu bar.  Go to "Plugin Manager" and choose "Show Plugin Manager"

> c. Find "Python Script" on this list and select it.  Then press the "Install" button.

3. You will need a script to do the find and replaces for you.  Lucky for you, I've already written one to do it.  Here's how to set it up:

> a. Open Notepad++

> b. Go to "Plugins", then "Python Script", then select "New Script"

> c. Type "ConvertShimeji" as the name.

> d. Choose "Encoding" on the Notepad++ menu bar and select "Encode in UTF-8"

> e. Copy and paste the following code into the file, then save the file.  Afterwards, the script will be ready to use as explained in the previous section:
```
editor.replace("<マスコット ", "<Mascot ")
editor.replace("<動作リスト>", "<ActionList>")
editor.replace("<動作 ", "<Action ")
editor.replace("名前=", "Name=")
editor.replace("\"振り向く\"", "\"Look\"")
editor.replace("種類=", "Type=")
editor.replace("\"組み込み\"", "\"Embedded\"")
editor.replace("クラス=", "Class=")
editor.replace("\"変位\"", "\"Offset\"")
editor.replace("<!-- 立つ系 -->", "<!-- Standing -->")
editor.replace("\"立つ\"", "\"Stand\"")
editor.replace("\"静止\"", "\"Stay\"")
editor.replace("枠=", "BorderType=")
editor.replace("\"地面\"", "\"Floor\"")
editor.replace("<アニメーション>", "<Animation>")
editor.replace("<ポーズ ", "<Pose ")
editor.replace("画像=", "Image=")
editor.replace("基準座標=", "ImageAnchor=")
editor.replace("移動速度=", "Velocity=")
editor.replace("長さ=", "Duration=")
editor.replace("</アニメーション>", "</Animation>")
editor.replace("</動作>", "</Action>")
editor.replace("\"歩く\"", "\"Walk\"")
editor.replace("\"移動\"", "\"Move\"")
editor.replace("\"走る\"", "\"Run\"")
editor.replace("\"猛ダッシュ\"", "\"Dash\"")
editor.replace("<!-- 座る系 -->", "<!-- Sitting -->")
editor.replace("\"座る\"", "\"Sit\"")
editor.replace("\"座って見上げる\"", "\"SitAndLookUp\"")
editor.replace("\"座ってマウスを見上げる\"", "\"SitAndLookAtMouse\"")
editor.replace("<アニメーション ", "<Animation ")
editor.replace("条件=", "Condition=")
editor.replace("</アニメーション>", "</Animation>")
editor.replace("\"座って首が回る\"", "\"SitAndSpinHeadAction\"")
editor.replace("\"固定\"", "\"Animate\"")
editor.replace("\"楽に座る\"", "\"SitWithLegsUp\"")
editor.replace("\"足を下ろして座る\"", "\"SitWithLegsDown\"")
editor.replace("\"足をぶらぶらさせる\"", "\"SitAndDangleLegs\"")
editor.replace("<!-- 寝そべる系 -->", "<!-- Laying -->")
editor.replace("\"寝そべる\"", "\"Sprawl\"")
editor.replace("\"ずりずり\"", "\"Creep\"")
editor.replace("<!-- 天井系 -->", "<!-- Ceiling -->")
editor.replace("\"天井に掴まる\"", "\"GrabCeiling\"")
editor.replace("\"天井\"", "\"Ceiling\"")
editor.replace("\"天井を伝う\"", "\"ClimbCeiling\"")
editor.replace("<!-- 壁系 -->", "<!-- Wall -->")
editor.replace("\"壁に掴まる\"", "\"GrabWall\"")
editor.replace("\"壁\"", "\"Wall\"")
editor.replace("\"壁を登る\"", "\"ClimbWall\"")
editor.replace("目的地Y", "TargetY")
editor.replace("目的地X", "TargetX")
editor.replace("<!-- IE系 -->", "<!-- IE -->")
editor.replace("\"IEを持って落ちる\"", "\"FallWithIe\"")
editor.replace("IEの端X=", "IeOffsetX=")
editor.replace("IEの端Y=", "IeOffsetY=")
editor.replace("\"IEを持って歩く\"", "\"WalkWithIe\"")
editor.replace("\"IEを持って走る\"", "\"RunWithIe\"")
editor.replace("\"IEを投げる\"", "\"ThrowIe\"")
editor.replace("初速X=", "InitialVX=")
editor.replace("初速Y=", "InitialVY=")
editor.replace("重力=", "Gravity=")
editor.replace("<!-- 落下系 -->", "<!-- Falling -->")
editor.replace("\"ジャンプ\"", "\"Jumping\"")
editor.replace("速度=", "VelocityParam=")
editor.replace("\"落ちる\"", "\"Falling\"")
editor.replace("空気抵抗X=", "RegistanceX=")
editor.replace("空気抵抗Y=", "RegistanceY=")
editor.replace("\"跳ねる\"", "\"Bouncing\"")
editor.replace("\"転ぶ\"", "\"Tripping\"")
editor.replace("<!-- ドラッグ系 -->", "<!-- Dragging -->")
editor.replace("\"つままれる\"", "\"Pinched\"")
editor.replace("\"抵抗する\"", "\"Resisting\"")
editor.replace("</動作リスト>", "</ActionList>")
editor.replace("<!-- 実際の行動 -->", "<!-- Actual Behavior -->")
editor.replace("<!-- システムが使用する -->", "<!-- ALWAYS REQUIRED -->")
editor.replace("\"落下する\"", "\"Fall\"")
editor.replace("\"複合\"", "\"Sequence\"")
editor.replace("繰り返し=", "Loop=")
editor.replace("<動作参照 ", "<ActionReference ")
editor.replace("\"選択\"", "\"Select\"")
editor.replace("\"ドラッグされる\"", "\"Dragged\"")
editor.replace("\"投げられる\"", "\"Thrown\"")
editor.replace("\"立ってボーっとする\"", "\"StandUp\"")
editor.replace("\"座ってボーっとする\"", "\"SitDown\"")
editor.replace("\"寝そべってボーっとする\"", "\"LieDown\"")
editor.replace("\"座って足をぶらぶらさせる\"", "\"SitWhileDanglingLegs\"")
editor.replace("\"壁に掴まってボーっとする\"", "\"HoldOntoWall\"")
editor.replace("\"壁から落ちる\"", "\"FallFromWall\"")
editor.replace("\"天井に掴まってボーっとする\"", "\"HoldOntoCeiling\"")
editor.replace("\"天井から落ちる\"", "\"FallFromCeiling\"")
editor.replace("\"ワークエリアの下辺を歩く\"", "\"WalkAlongWorkAreaFloor\"")
editor.replace("\"ワークエリアの下辺を走る\"", "\"RunAlongWorkAreaFloor\"")
editor.replace("\"ワークエリアの下辺でずりずり\"", "\"CrawlAlongWorkAreaFloor\"")
editor.replace("\"ワークエリアの下辺の左の端っこで座る\"", "\"WalkLeftAlongFloorAndSit\"")
editor.replace("\"ワークエリアの下辺の右の端っこで座る\"", "\"WalkRightAlongFloorAndSit\"")
editor.replace("右向き=", "LookRight=")
editor.replace("\"ワークエリアの下辺から左の壁によじのぼる\"", "\"GrabWorkAreaBottomLeftWall\"")
editor.replace("\"ワークエリアの下辺から右の壁によじのぼる\"", "\"GrabWorkAreaBottomRightWall\"")
editor.replace("\"走ってワークエリアの下辺の左の端っこで座る\"", "\"WalkLeftAndSit\"")
editor.replace("\"走ってワークエリアの下辺の右の端っこで座る\"", "\"WalkRightAndSit\"")
editor.replace("\"走ってワークエリアの下辺から左の壁によじのぼる\"", "\"WalkAndGrabBottomLeftWall\"")
editor.replace("\"走ってワークエリアの下辺から右の壁によじのぼる\"", "\"WalkAndGrabBottomRightWall\"")
editor.replace("\"IEの下に飛びつく\"", "\"JumpFromBottomOfIE\"")
editor.replace("\"ワークエリアの壁を途中まで登る\"", "\"ClimbHalfwayAlongWall\"")
editor.replace("<行動リスト>", "<BehaviorList>")
editor.replace("<行動 ", "<Behavior ")
editor.replace("頻度=", "Frequency=")
editor.replace("<次の行動リスト ", "<NextBehavior ")
editor.replace("追加=", "Add=")
editor.replace("</次の行動リスト>", "</NextBehavior>")
editor.replace("</行動>", "</Behavior>")
editor.replace("\"マウスの周りに集まる\"", "\"ChaseMouse\"")
editor.replace("<行動参照 ", "<BehaviorReference ")
editor.replace("\"座ってマウスのほうを見る\"", "\"SitAndFaceMouse\"")
editor.replace("\"座ってマウスのほうを見てたら首が回った\"", "\"SitAndSpinHead\"")
editor.replace("\"引っこ抜かれる\"", "\"PullUp\"")
editor.replace("\"分裂した\"", "\"Divided\"")
editor.replace("<!-- 地面に接しているとき -->", "<!-- On the Floor -->")
editor.replace("<条件 ", "<Condition ")
editor.replace("\"分裂する\"", "\"SplitIntoTwo\"")
editor.replace("</条件>", "</Condition>")
editor.replace("<!-- 壁に接しているとき -->", "<!-- On the Wall -->")
editor.replace("<!-- 天井に接しているとき -->", "<!-- On the Ceiling -->")
editor.replace("<!-- ワークエリアの下辺に接しているとき -->", "<!-- On Work Area Floor -->")
editor.replace("<!-- ずりずりした後はそのままボーっとする -->", "<!-- Finished Crawling -->")
editor.replace("\"引っこ抜く\"", "\"PullUpShimeji\"")
editor.replace("<!-- ワークエリアの壁に接しているとき -->", "<!-- On Work Area Facing the Wall -->")
editor.replace("\"ワークエリアの壁を登る\"", "\"ClimbAlongWall\"")
editor.replace("<!-- ワークエリアの上辺に接しているとき -->", "<!-- On Work Area Top Facing -->")
editor.replace("\"ワークエリアの上辺を伝う\"", "\"ClimbAlongCeiling\"")
editor.replace("<!-- IEの上辺に接しているとき -->", "<!-- On Top of IE -->")
editor.replace("\"IEの天井を歩く\"", "\"WalkAlongIECeiling\"")
editor.replace("\"IEの天井を走る\"", "\"RunAlongIECeiling\"")
editor.replace("\"IEの天井でずりずり\"", "\"CrawlAlongIECeiling\"")
editor.replace("<!-- ずりずりした後はそのままボーっとする -->", "<!-- Finished Crawling -->")
editor.replace("\"IEの天井の左の端っこで座る\"", "\"SitOnTheLeftEdgeOfIE\"")
editor.replace("\"IEの天井の右の端っこで座る\"", "\"SitOnTheRightEdgeOfIE\"")
editor.replace("\"IEの天井の左の端っこから飛び降りる\"", "\"JumpFromLeftEdgeOfIE\"")
editor.replace("\"IEの天井の右の端っこから飛び降りる\"", "\"JumpFromRightEdgeOfIE\"")
editor.replace("\"走ってIEの天井の左の端っこで座る\"", "\"WalkLeftAlongIEAndSit\"")
editor.replace("\"走ってIEの天井の右の端っこで座る\"", "\"WalkRightAlongIEAndSit\"")
editor.replace("\"走ってIEの天井の左の端っこから飛び降りる\"", "\"WalkLeftAlongIEAndJump\"")
editor.replace("\"走ってIEの天井の右の端っこから飛び降りる\"", "\"WalkRightAlongIEAndJump\"")
editor.replace("<!-- IEの壁に接しているとき -->", "<!-- On IE's Side -->")
editor.replace("\"IEの壁を途中まで登る\"", "\"HoldOntoIEWall\"")
editor.replace("\"IEの壁を登る\"", "\"ClimbIEWall\"")
editor.replace("<!-- IEの下辺に接しているとき -->", "<!-- On the Bottom of IE -->")
editor.replace("\"IEの下辺を伝う\"", "\"ClimbIEBottom\"")
editor.replace("\"IEの下辺から左の壁によじのぼる\"", "\"GrabIEBottomLeftWall\"")
editor.replace("\"IEの下辺から右の壁によじのぼる\"", "\"GrabIEBottomRightWall\"")
editor.replace("\"左の壁に飛びつく\"", "\"JumpFromLeftWall\"")
editor.replace("\"右の壁に飛びつく\"", "\"JumpFromRightWall\"")
editor.replace("<!-- IEが見えるとき -->", "<!-- IE Is Visible -->")
editor.replace("\"IEの左に飛びつく\"", "\"JumpOnIELeftWall\"")
editor.replace("\"IEの右に飛びつく\"", "\"JumpOnIERightWall\"")
editor.replace("\"IEを右に投げる\"", "\"ThrowIEFromLeft\"")
editor.replace("\"IEを左に投げる\"", "\"ThrowIEFromRight\"")
editor.replace("\"走ってIEを右に投げる\"", "\"WalkAndThrowIEFromRight\"")
editor.replace("\"走ってIEを左に投げる\"", "\"WalkAndThrowIEFromLeft\"")
editor.replace("</行動リスト>", "</BehaviorList>")
editor.replace("</マスコット>", "</Mascot>")
editor.replace("#{footX", "#{FootX")
editor.replace("\"猛ダッシュでIEの天井の左の端っこから飛び降りる\"", "\"DashIeCeilingLeftEdgeFromJump\"")
editor.replace("\"猛ダッシュでIEの天井の右の端っこから飛び降りる\"", "\"DashIeCeilingRightEdgeFromJump\"")
editor.replace("\"猛ダッシュでIEの天井の左の端っこから飛び降りる\"", "\"DashIeCeilingLeftEdgeFromJump\"")
editor.replace("\"猛ダッシュでIEの天井の右の端っこから飛び降りる\"", "\"DashIeCeilingRightEdgeFromJump\"")
editor.replace("ずれ=", "Gap=")
editor.replace("+ずれ", "+Gap")
editor.replace("\"引っこ抜く1\"", "\"PullUpShimeji1\"")
editor.replace("生まれる場所X=", "BornX=")
editor.replace("生まれる場所Y=", "BornY=")
editor.replace("生まれた時の行動=", "BornBehavior=")
editor.replace("\"引っこ抜く2\"", "\"PullUpShimeji2\"")
editor.replace("\"分裂1\"", "\"Divide1\"")
```