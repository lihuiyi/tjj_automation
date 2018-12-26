package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Switch {
	
	/*iframe和iframe之间切换是：一层层的切进去、一层层切出来
	  iframe和html主文档之间切换是：直接切进去、直接切出来*/
	//切换到iframe，参数：xpath
	public void toFrame(WebDriver driver, String xpath){
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}
	
	
	
	//从iframe切换到主文档
	public void toDefaultContent(WebDriver driver){
		driver.switchTo().defaultContent();
	}
	
	
	//切换窗口（有控制权）,参数：目标页面标题,TAB标签序号
	public void toWindow(WebDriver driver, String targetTitle, int TABNum){
		Set<String> allHandles = driver.getWindowHandles(); //获取所有页面的handles
		String title = null; //页面标题
		for(String handle : allHandles){
			title = driver.switchTo().window(handle).getTitle(); //获取每一个页面的标题
			if(title.contains(targetTitle)){ //如果title包含目标页面标题
				driver = driver.switchTo().window(handle); //获取页面控制权
				this.toTAB(TABNum);
				break;
			}
		}
	}
	
	
	
	//快捷键切换窗口（无控制权），参数：TAB标签序号
	public void toTAB(int TABNum) {
  	Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL); //按下ctrl
		//按下并释放1到9的数字键
		if(TABNum == 1){
			robot.keyPress(java.awt.event.KeyEvent.VK_1); //按下并释放数字键1
		}else if(TABNum == 2){
			robot.keyPress(java.awt.event.KeyEvent.VK_2);
		}else if(TABNum == 3){
			robot.keyPress(java.awt.event.KeyEvent.VK_3);
		}else if(TABNum == 4){
			robot.keyPress(java.awt.event.KeyEvent.VK_4);
		}else if(TABNum == 5){
			robot.keyPress(java.awt.event.KeyEvent.VK_5);
		}else if(TABNum == 6){
			robot.keyPress(java.awt.event.KeyEvent.VK_6);
		}else if(TABNum == 7){
			robot.keyPress(java.awt.event.KeyEvent.VK_7);
		}else if(TABNum == 8){
			robot.keyPress(java.awt.event.KeyEvent.VK_8);
		}else if(TABNum == 9){
			robot.keyPress(java.awt.event.KeyEvent.VK_9);
		}
	  	robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL); //释放ctrl
  }
}


