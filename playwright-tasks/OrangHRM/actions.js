const delay = (ms) => new Promise(res => setTimeout(res, ms));

exports.ActionHelper = class ActionHelper {
    constructor(page, delayMs = 2000) {
        this.page = page;
        this.delayMs = delayMs;
    }

    async click(selector) {
        await this.page.click(selector);
        await delay(this.delayMs);
    }

    async fill(selector, value) {
        await this.page.fill(selector, value);
        await delay(this.delayMs);
    }

    async press(key) {
        await this.page.keyboard.press(key);
        await delay(this.delayMs);
    }

    async waitForSelector(selector) {
        await this.page.waitForSelector(selector);
        await delay(this.delayMs);
    }

    // Add more wrapped methods if needed
};
