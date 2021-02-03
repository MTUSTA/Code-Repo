from selenium import webdriver
# pip install webdriver_manager then restart
from webdriver_manager.chrome import ChromeDriverManager
from selenium.common.exceptions import NoSuchElementException
# sleep
import time


class KlanlarBot():
    def __init__(self):
        # if process or program use this file --> give an error --> permission denied
        self.browser = webdriver.Chrome(ChromeDriverManager().install())
        self.browser.maximize_window()
        self.browser.get('https://www.klanlar.org/')

    def check_exists_by_xpath(self, xpath):
        try:
            self.browser.find_element_by_xpath(xpath)
        except NoSuchElementException:
            return False
        return True

    def create_new_account(self):
        pass

    def login(self):
        username = self.browser.find_element_by_xpath('//*[@id="user"]')
        username.send_keys('--mer-ter--')
        password = self.browser.find_element_by_xpath('//*[@id="password"]')
        password.send_keys('1a170507dff9')
        # button
        time.sleep(2)
        self.browser.find_element_by_xpath('//*[@id="login_form"]/div/div/a').click()
        time.sleep(2)
        self.browser.find_element_by_xpath(
            '//*[@id="home"]/div[3]/div[4]/div[10]/div[3]/div[2]/div[1]/a[2]/span').click()

    def bina_basma(self):
        self.browser.find_element_by_xpath('//*[@id="menu_row"]/td[2]/a').click()
        # Ana bina
        self.browser.find_element_by_xpath('//*[@id="l_main"]/td/a').click()

    def barbara_saldiri(self):
        self.browser.find_element_by_xpath('//*[@id="menu_row"]/td[2]/a').click()
        time.sleep(2)
        # köy adi
        # kordinat = self.browser.find_element_by_xpath('//*[@id="menu_row2"]/td[2]/b').text
        # içtima meydanı
        self.browser.find_element_by_xpath('//*[@id="l_place"]/td/a').click()

        self.browser.find_element_by_xpath('//*[@id="command_target"]/table/tbody/tr[1]/td/div[1]/label[2]/input').click()
        hedef = self.browser.find_element_by_xpath('//*[@id="place_target"]/input')
        hedef.send_keys('barbar köyü')

        time.sleep(2)
        # daha fazla
        for i in range(10, 20, 9):
            if (self.check_exists_by_xpath('//*[@id="ds_body"]/div[12]/div[' + str(i) + ']')):
                self.browser.find_element_by_xpath('//*[@id="ds_body"]/div[12]/div[' + str(i) + ']').click()
            else:
                break
            time.sleep(2)

        barbar_listesi = self.browser.find_elements_by_class_name('village-item')
        print(barbar_listesi)

    def quit_klanlar(self):
        self.browser.find_element_by_xpath('//*[@id="linkContainer"]/a[7]').click()
        self.browser.close()


bot1 = KlanlarBot()
menu = 'Yeni kayıt için 1\'e' \
       '\nBotu Başlatmak için 2\'e' \
       '\nÖncelikleri ayarlamak için 3\'e basınız' \
       '\nÇıkış için 4\'e basınız\n'
tercihler = {}
while (1):
    secim = int(input(menu))
    if secim == 1:
        pass
    elif secim == 2:
        bot1.login()
        time.sleep(2)
        #bot1.bina_basma()
        time.sleep(2)
        #bot1.barbara_saldiri()
    elif secim == 3:
        print()
    elif secim == 4:
        bot1.quit_klanlar()
        break
