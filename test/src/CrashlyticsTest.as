package
{
	import com.freshplanet.ane.AirCrashlytics.AirCrashlytics;
	
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFormatAlign;
	
	public class CrashlyticsTest extends Sprite
	{
		// PROPERTIES :
		private var dy:Number;
		
		// CONSTRUCTOR
		public function CrashlyticsTest() {
			super();
			
			stage.align = StageAlign.TOP_LEFT;
			stage.scaleMode = StageScaleMode.SHOW_ALL;
			
			initDisplay();
			initCrashlytics();
		}
		
		private function initCrashlytics():void {
			trace("Initializing Crashlytics ...");
			
			AirCrashlytics.start();
			
			trace("Crashlytics initialized.");
		}
		
		private function initDisplay():void {
			dy = 30;
			
			addButton("Crash", crash);
			
		}
		
		private function addButton(label:String, onClick:Function):void {
			var b:Sprite = new Sprite();
			b.graphics.beginFill(0xbbbbbb);
			b.graphics.lineStyle(1, 0x222222, 1, true);
			b.graphics.drawRect(0, 0, stage.stageWidth - 20, 40);
			b.graphics.endFill();
			b.x = 10;
			b.y = dy;
			dy += 70;
			
			var tf:TextField = new TextField();
			tf.defaultTextFormat = new TextFormat("Arial", 14, 0, true, null, null, null, null, TextFormatAlign.CENTER);
			tf.text = label;
			tf.x = 10;
			tf.y = 10;
			tf.selectable = false;
			tf.width = stage.stageWidth - 40;
			tf.height = 20;
			b.addChild(tf);
			
			b.addEventListener(MouseEvent.CLICK, function(ev:MouseEvent):void { onClick(); });
			
			addChild(b);
		}
		
		private function crash():void {
			trace("Crashing app...");
			AirCrashlytics.crash();
		}
	}
}