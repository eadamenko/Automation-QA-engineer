// Test  
@Test (description = "ticketname1", dataProvider = "FAVORITES_CART_VISIBLE")
	public void cartButtonOnFavorites(String login, String password, boolean isPresent) {
	  	app.getUserHelper().directLogin(new User(login, password));
		app.getNavigationHelper().openFavorites();
		app.getFavoritesHelper().changeFavoritesView(true);
		Assert.assertEquals(app.getFavoritesHelper().isAddToCartButtonPresent(), isPresent);
		
		app.getFavoritesHelper().changeFavoritesView(false);
		Assert.assertEquals(app.getFavoritesHelper().isAddToCartButtonPresent(), isPresent);
	  }
    
// DataProvider      
@DataProvider(name = "FAVORITES_CART_VISIBLE") //login, password, visibility of element
	public Object[][] favoritesCartVisible() {
		return new Object[][]{
				{"testUserTicketname1", "123456", true},
				{"testUserTicketname1_Enterprise", "123456", false}
		};
	}
  
// FavoritesHelper  
void changeFavoritesView(boolean isTableView);
boolean isAddToCartButtonPresent();


// FavoritesHelperDriver   
@Override
	public void changeFavoritesView(boolean isTableView) {
		logMe(isTableView);
		pages2.favoritesPage2.changeFavoritesView(isTableView);
	}
  
@Override
	public boolean isAddToCartButtonPresent() {
		logMe();
		return pages2.favoritesPage2.isAddToCartButtonPresent();
	}  
  
  
// FavoritesPage  
@FindBy(xpath = "//i[contains(@class, 'change-view-grid')]")
	private WebElement changeViewButton; 
  
By gridViewFolders = By.xpath("//div[contains(@class, 'grid-default')]");  

By addToCartButtonOnGrid = By.xpath("//button[contains (@class, 'add-to-cart')]");

By addToCartButton = By.xpath("//i[contains(@class, 'button-action-add-to-cart')]");

public void changeFavoritesView(boolean isTableView) {
		if (isTableView != driver.findElements(By.cssSelector("table[class*='favorites-table']")).size() > 0 )
			wait.until(ExpectedConditions.elementToBeClickable(changeViewButton)).click();
	}  
  
public boolean isAddToCartButtonPresent() {
		if (driver.findElements(gridViewFolders).size() > 0) {
			return driver.findElements(addToCartButtonOnGrid).size() > 0;
		}
		return driver.findElements(addToCartButton).size() > 0;
	}  
