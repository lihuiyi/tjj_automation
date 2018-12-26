package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Switch {
	
	/*iframe��iframe֮���л��ǣ�һ�����н�ȥ��һ����г���
	  iframe��html���ĵ�֮���л��ǣ�ֱ���н�ȥ��ֱ���г���*/
	//�л���iframe��������xpath
	public void toFrame(WebDriver driver, String xpath){
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}
	
	
	
	//��iframe�л������ĵ�
	public void toDefaultContent(WebDriver driver){
		driver.switchTo().defaultContent();
	}
	
	
	//�л����ڣ��п���Ȩ��,������Ŀ��ҳ�����,TAB��ǩ���
	public void toWindow(WebDriver driver, String targetTitle, int TABNum){
		Set<String> allHandles = driver.getWindowHandles(); //��ȡ����ҳ���handles
		String title = null; //ҳ�����
		for(String handle : allHandles){
			title = driver.switchTo().window(handle).getTitle(); //��ȡÿһ��ҳ��ı���
			if(title.contains(targetTitle)){ //���title����Ŀ��ҳ�����
				driver = driver.switchTo().window(handle); //��ȡҳ�����Ȩ
				this.toTAB(TABNum);
				break;
			}
		}
	}
	
	
	
	//��ݼ��л����ڣ��޿���Ȩ����������TAB��ǩ���
	public void toTAB(int TABNum) {
  	Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL); //����ctrl
		//���²��ͷ�1��9�����ּ�
		if(TABNum == 1){
			robot.keyPress(java.awt.event.KeyEvent.VK_1); //���²��ͷ����ּ�1
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
	  	robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL); //�ͷ�ctrl
  }
}


