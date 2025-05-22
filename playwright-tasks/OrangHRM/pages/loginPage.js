exports.LoginPage = class LoginPage {
    constructor(page) {
        this.page = page;

        // Locators
        this.usernameField = page.locator('input[name="username"]');
        this.passwordField = page.locator('input[name="password"]');
        this.loginBtn = page.locator('button[type="submit"]');
        this.dashboardTitle = page.locator('h6:has-text("Dashboard")');
    }

    async login(username, password) {
        await this.usernameField.fill(username);
        await this.passwordField.fill(password);
        await this.loginBtn.click();

        try {
            await this.dashboardTitle.waitFor({ timeout: 5000 });
            console.log('Landed on the dashboard');
            return true;
        } catch (e) {
            console.error('Login failed or Dashboard not loaded');
            return false;
        }
    }
};
