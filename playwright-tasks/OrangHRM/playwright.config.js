// playwright.config.js
module.exports = {
  use: {
    headless: false,
    viewport: { width: 1280, height: 720 },
    screenshot: 'on',
        video: 'retain-on-failure',
        actionTimeout : 10000
  },
  retries: 0,
  reporter: 'list'
};