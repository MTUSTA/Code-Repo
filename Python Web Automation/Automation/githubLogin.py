from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

browser = webdriver.Chrome(ChromeDriverManager().install())
browser.get('https://github.com/login')

github_username = browser.find_element_by_xpath('//*[@id="login_field"]')
github_username.send_keys("b21527472")

github_password = browser.find_element_by_xpath('//*[@id="password"]')
github_password.send_keys("coitiblacho1997")

login_button = browser.find_element_by_xpath('//*[@id="login"]/form/div[4]/input[9]')
login_button.click()

#create repo diye devam edebilir
