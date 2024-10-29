package com.example.demo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class SystemTest {
	WebDriver driver;
	
	
	@BeforeEach
    public void setUp() {
        // Manually set the path to ChromeDriver
		//غير مكان الدرافير لمكان الدرافير الي عندك
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("http://localhost:8080/notes");
        
	}
	
	@AfterEach
    public void tearDown() {
//         Quit the driver if it's initialized
        if (driver != null) {
            driver.quit();
            
        }
        
        
	}
	
	@Test
	public void createNote() {
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("note1");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("Hello World!");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		boolean edit = driver.findElement(By.linkText("Edit")).isDisplayed();
		assertTrue(edit,"Edit link should be displayed");
		
	}
	
	@Test
	public void editNote() {
		//create a note
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("note1");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("Hello World!!");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		//edit the created note
		
		WebElement edit = driver.findElement(By.xpath("//tr[td[text()='Hello World!!']]/td/a[contains(text(), 'Edit')]"));
		edit.click();
		
		WebElement eTitleField = driver.findElement(By.name("title"));
		eTitleField.sendKeys("2");
		
		WebElement eContentField = driver.findElement(By.name("content"));
		eContentField.clear();
		eContentField.sendKeys("Hello!!!");
		
		WebElement esave = driver.findElement(By.tagName("button"));
		esave.click();
		
		boolean edited = driver.findElement(By.xpath("//*[text()='Hello!!!']")).isDisplayed();
		assertTrue(edited, "the content displayed should be 'Hello!!!'");
		
	}
	
	@Test
	public void testDeleteNote() {
		//create a note 
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("note1");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("DELETE ME");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		
		boolean there = !driver.findElement(By.xpath("//*[text()='DELETE ME']")).isDisplayed();
		
		//delete the created note
		
		
		WebElement delete = driver.findElement(By.xpath("//tr[td[text()='DELETE ME']]/td/a[contains(text(), 'Delete')]"));
		delete.click();
		
		
		assertTrue(!there, "the note should be deleted");
	}
	
	@Test
	public void testCreateNullAllNote() {
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
	}
	
	@Test
	public void testCreateNullTitleNote() {
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("Hellooo");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
		
	}
	
	@Test
	public void testCreateNullContentNote() {
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("note of nothing");
		
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
		
	}
	
	@Test
	public void testEditNoteWithNullAll() {
		//creating a new note 
		
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("heloo");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("EDIT ME1");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		//editing the created note
		
		WebElement edit = driver.findElement(By.xpath("//tr[td[text()='EDIT ME1']]/td/a[contains(text(), 'Edit')]"));
		edit.click();
		
		WebElement eTitleField = driver.findElement(By.name("title"));
		eTitleField.clear();
		
		WebElement eContentField = driver.findElement(By.name("content"));
		eContentField.clear();
		
		WebElement esave = driver.findElement(By.tagName("button"));
		esave.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
		
	}
	
	@Test
	public void testEditNoteWithNullTitle() {
		//creating a new note 
		
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("heloo");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("EDIT ME2");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		//editing the created note
		
		WebElement edit = driver.findElement(By.xpath("//tr[td[text()='EDIT ME2']]/td/a[contains(text(), 'Edit')]"));
		edit.click();
		
		WebElement eTitleField = driver.findElement(By.name("title"));
		eTitleField.clear();
		
		WebElement eContentField = driver.findElement(By.name("content"));
		eContentField.sendKeys(" Hello");
		
		WebElement esave = driver.findElement(By.tagName("button"));
		esave.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
		
	}
	
	@Test
	public void testEditNoteWithNullContent() {
		//creating a new note 
		
		WebElement newnote = driver.findElement(By.tagName("a"));
		newnote.click();
		
		WebElement titleField = driver.findElement(By.name("title"));
		titleField.sendKeys("heloo");
		
		WebElement contentField = driver.findElement(By.name("content"));
		contentField.sendKeys("EDIT ME3");
		
		WebElement save = driver.findElement(By.tagName("button"));
		save.click();
		
		//editing the created note
		
		WebElement edit = driver.findElement(By.xpath("//tr[td[text()='EDIT ME3']]/td/a[contains(text(), 'Edit')]"));
		edit.click();
		
		WebElement eTitleField = driver.findElement(By.name("title"));
		eTitleField.sendKeys("2");
		
		WebElement eContentField = driver.findElement(By.name("content"));
		eContentField.clear();
		
		WebElement esave = driver.findElement(By.tagName("button"));
		esave.click();
		
		WebElement header = driver.findElement(By.tagName("h1"));
		assertTrue(header.isDisplayed(),"page should not change");
		
	}
	
	
	
	
	
	
	
	
	
	
}
