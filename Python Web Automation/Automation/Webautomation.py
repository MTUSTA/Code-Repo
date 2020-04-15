from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

import time

browser = webdriver.Chrome(ChromeDriverManager().install())
browser.maximize_window()
browser.get('https://www.youtube.com/')

searchbox = browser.find_element_by_xpath('//*[@id="search"]')
searchbox.send_keys("Spirit of the law")

searchbutton = browser.find_element_by_xpath('//*[@id="search-icon-legacy"]')
searchbutton.click()

# save main_window
main_window = browser.current_window_handle

# open new blank tab
browser.execute_script("window.open();")

# switch to the new window which is second in window_handles array
browser.switch_to_window(browser.current_window_handle)

time.sleep(2)
# switch to the new window which is second in window_handles array
browser.switch_to_window(browser.window_handles[1])

time.sleep(2)
# switch to the new window which is second in window_handles array
browser.switch_to_window(browser.window_handles[0])
