// Test  
@Test( dataProvider = "EMPTY_SEARCH_URLS", description = "ticketname2")
	public void emptySearchNotClickableNewOldSite (String url) {
		User user = new User("testUserticketname2", "123456");
		for (int i = 0; i < 2; i++) {
			if (i > 0) {
				app.getUserHelper().directLogin(user, url);
			} else {
				app.getNavigationHelper().openRelativeUrl(url);
			}
			app.getSearchHelper().clickSearchButton();
			Assert.assertTrue(app.getCurrentUrl().contains(url));
		}
	}

// DataProvider  
@DataProvider(name = "EMPTY_SEARCH_URLS")
	public Object[][] emptySearchUrls() {
		return new Object[][]{
				{"/stock-photography.html"},
				{"/5662441/stock-photo-terrorist.html"},
				{"/member-agreement.html"}
		};
	}  
  
  
// SearchHelper   
void clickSearchButton(); 
 
// SearchHelperDriver
@Override
	public void clickSearchButton() {
		logMe();
		pages2.searchPage.clickSearchButton();
	} 
  
// SearchPage
@FindBy(xpath = "//button[contains(@class,'search-bar__button-submit')]")
	private WebElement searchButton; 

public void clickSearchButton() {
		if (isOldHeader()) {
			driver.findElement(By.xpath("//div[@class='search-right']/button")).click();
		} else {
			searchButton.click();
		}
	}
  
 
