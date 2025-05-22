const { expect } = require('@playwright/test');
exports.PIMPage = class PIMPage {
    constructor(page) {
        this.page = page;
    

        // Locators
        this.addEmployeeBtn = page.locator('button:has-text("Add")');
        this.addEmployeeTitle = page.locator('h6:has-text("Add Employee")');
        this.firstNameTextField = page.locator('input[name="firstName"]');
        this.lastNameTextField = page.locator('input[name="lastName"]');
        this.employeeIdTextField = page.locator('//label[text() = "Employee Id"]/ancestor::div/following-sibling::div/input');

        this.createLoginDetailsBtn = page.locator('span.oxd-switch-input.oxd-switch-input--active.--label-right');
        this.userNameTextField = page.locator('//label[text()= "Username"]/ancestor::div/following-sibling::div/input');
        this.passwordTextField = page.locator('//label[text()= "Password"]/ancestor::div/following-sibling::div/input');
        this.confirmPasswordTextField = page.locator('//label[text()= "Confirm Password"]/ancestor::div/following-sibling::div/input');
        this.saveBtn = page.locator('button[type="button"]');

        this.personalDetailsText = page.locator('h6:has-text("Personal Details")');
        this.pimTab = page.locator('a:has(span:has-text("PIM"))');
        this.searchBtn = page.locator('button[type="submit"]');
        this.firstNameInRecords = page.locator('//div[@class="oxd-table-card"]/child::div/child::div[3]');
        this.recordFoundText = page.locator('//div[@class="orangehrm-horizontal-padding orangehrm-vertical-padding"]/span');

        this.profileDropDown = page.locator('span.oxd-userdropdown-tab');
        this.logoutBtn = page.locator('//a[text() = "Logout"]');
        this.loginText = page.locator('h5.oxd-text.oxd-text--h5.orangehrm-login-title');
    }

    async employeeId() {
        return await this.employeeIdTextField.inputValue();
    }

    async addNewEmployeeAndVerify() {
        const uuid = () => Math.random().toString(36).substring(2, 8);
        const firstName = `First_${uuid()}`;
        const lastName = `Last_${uuid()}`;
        const uniqueUsername = `user_1${uuid()}`;
        const uniquePassword = `pass_1${uuid()}`;

        await this.addEmployeeBtn.click();
        await this.addEmployeeTitle.waitFor();

        const empId = await this.employeeId();
        console.log("Employee id:", empId);

        await this.firstNameTextField.fill(firstName);
        await this.lastNameTextField.fill(lastName);

        const enteredFirstName = await this.firstNameTextField.inputValue();
        const enteredLastName = await this.lastNameTextField.inputValue();

        console.log("Entered First Name:", enteredFirstName);
        console.log("Entered Last Name:", enteredLastName);

        await this.createLoginDetailsBtn.click();
        await this.userNameTextField.fill(uniqueUsername);
        await this.passwordTextField.fill(uniquePassword);
        await this.confirmPasswordTextField.fill(uniquePassword);
        await this.page.keyboard.press('Enter');

        await this.personalDetailsText.waitFor();

        await this.pimTab.click();
        await this.employeeIdTextField.fill(empId);
        await this.searchBtn.click();

        await this.recordFoundText.waitFor();
        const firstNameInTable = await this.firstNameInRecords.textContent();

        expect(firstNameInTable.trim()).toBe(enteredFirstName);
        console.log("Record Verified from the Employee list!");

    }

    async logout() {
        await this.profileDropDown.click();
        await this.logoutBtn.click();
        try {
            await this.loginText.waitFor({ timeout: 5000 });
            console.log("Logged out Successfully");
            return true;
        } catch (e) {
            return false;
        }
    }
};
