exports.DashboardPage = class DashboardPage {
    constructor(page) {
        this.page = page;

        // Locators
        this.pimTab = page.locator('a:has(span:has-text("PIM"))');
        this.pimTitle = page.locator('h6:has-text("PIM")');
    }

    async clickPIM() {
        await this.pimTab.click();
        await this.pimTitle.waitFor({ timeout: 5000 });
        console.log('Landed on the PIM page');
    }
};
