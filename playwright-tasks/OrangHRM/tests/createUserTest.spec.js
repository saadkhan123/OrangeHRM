const { test, expect } = require('@playwright/test');
const { LoginPage } = require('../pages/loginPage');
const { DashboardPage } = require('../pages/dashboardPage');
const { PIMPage } = require('../pages/pimPage');

test('Add and verify employee in OrangeHRM', async ({ page }) => {
  const loginPage = new LoginPage(page);
  const dashboardPage = new DashboardPage(page);
  const pimPage = new PIMPage(page);

  await page.goto('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');

  const loggedIn = await loginPage.login('Admin', 'admin123');
  expect(loggedIn).toBeTruthy();

  await dashboardPage.clickPIM();
  await pimPage.addNewEmployeeAndVerify();
  const loggedOut = await pimPage.logout();
  expect(loggedOut).toBeTruthy();
});
