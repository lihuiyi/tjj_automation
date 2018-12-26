package 清查对比系统;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import util.Switch;
import util.Wait;


public class People {
	WebDriver driver = null;
	Switch switch_obj = null;
	Wait wait = null;
	Actions actions = null;
	int timeout = 0;
	// 需要打断点：login,frameMain
	
	

	public People(String ChromePath, String ChromeDriverPath, int timeout) {
		System.setProperty("webdriver.chrome.bin", ChromePath);
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // 最大化浏览器
		actions = new Actions(driver);
		switch_obj = new Switch();
		wait = new Wait();
		this.timeout = timeout;
	}
	
	

	public static void main(String[] args) {
		String ChromePath = "D:\\chrome\\Application\\chrome.exe";
		String ChromeDriverPath = "D:\\\\chrome\\\\Application\\chromedriver.exe";
		int timeout = 30;
		String url = "http://10.53.6.2:8080/MC";
		String userName = "530124_ml1";
		String passWord = "530124Fmtjj";
		
		People people = new People(ChromePath, ChromeDriverPath, timeout);
		people.login(url, userName, passWord);
		people.run();
	}
	
	

	public void login(String url, String userName, String passWord) {
		driver.get(url);
		// 登陆
		WebElement user_name = driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/div/input"));
		user_name.clear();
		user_name.sendKeys(userName);
		WebElement pass_word = driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[3]/div/input"));
		pass_word.clear();
		pass_word.sendKeys(passWord);
		// 此处需要打断点
		WebElement login = driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[6]/a"));
		login.click();
	}
	
	
	

	public void run(){
		// 单位清查库库
		wait.element_presence(driver, timeout, "//*[@id='menu2']/a");
		WebElement dwqck = driver.findElement(By.xpath("//*[@id='menu2']/a"));
		dwqck.click();
		// 此处需要打断点。等待手动执行 清查结果数据处理、个体经营户清查表
		switch_obj.toFrame(driver, "//*[@id='frameMain']");
		switch_obj.toFrame(driver, "//*[@id='tableframe']");
		// 执行表格中的内容
		int y = 2;
		int t = 15;
		for(int i=0; i<y; i++){
			// 当前页的15条数据
			for(int j=0; j<t; j++){
				run_table(driver, actions, 30, j);
			}
			// 下一页
			String xpath = "/html/body/div[1]/div[2]/div/div/div/div[2]/table/tbody/tr/td[10]/a";
			wait.element_presence(driver, timeout, xpath);
			WebElement next_y = driver.findElement(By.xpath(xpath));
			next_y.click();
		}
	}
	
	
	

	public void run_table(WebDriver driver, Actions actions, int timeout, int j){
		//鼠标击表格中的第一行
		String xpath = "//*[@id='datagrid-row-r1-2-" + j + "']" + "/td[3]/div";
		wait.element_presence(driver, timeout, xpath);
		WebElement first_row = driver.findElement(By.xpath(xpath));
		actions.doubleClick(first_row).build().perform(); // 鼠标双击指定的元素
		switch_obj.toWindow(driver, "个体经营户清查情况——数据编辑", 2);
		
		// 从业人数
		xpath = "//*[@id='businessData']/tbody/tr[21]/td[2]/div";
		wait.element_presence(driver, timeout, xpath);
		WebElement cyrs_div = driver.findElement(By.xpath(xpath));
		String text = cyrs_div.getAttribute("innerText");
		actions.doubleClick(cyrs_div).build().perform(); // 鼠标双击指定的元素
		
		xpath = "//*[@id='businessData']/tbody/tr[21]/td[2]/input";
		WebElement cyrs_input = driver.findElement(By.xpath(xpath));
		actions.doubleClick(cyrs_input).build().perform(); // 鼠标双击指定的元素
		cyrs_input.clear();
		
		// 人数
		String new_text = "3";
		cyrs_input.sendKeys(new_text);
		
		//保存
		wait.element_presence(driver, timeout, "//*[@id='btns']/button[1]");
		WebElement save = driver.findElement(By.xpath("//*[@id='btns']/button[1]"));
		save.click();
		
		// 确定按钮
		wait.element_presence(driver, timeout, "/html/body/div[5]/div[2]/div[4]/a/span/span");
		WebElement ok = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[4]/a/span/span"));
		ok.click();
		
		
		driver.close();
		switch_obj.toWindow(driver, "第四次全国经济普查-智能比对系统", 1);
		switch_obj.toDefaultContent(driver);
		switch_obj.toFrame(driver, "//*[@id='frameMain']");
		switch_obj.toFrame(driver, "//*[@id='tableframe']");
	}
	
	
}
