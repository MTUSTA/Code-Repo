import os
import os.path
import sys
import platform

# locations and names used the same as in setupy_download_helper.py
__base_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'bin')
__base_name = 'chromedriver'
__platform_name = platform.system()
if __platform_name  == 'Linux':
    __platform_name = 'Linux64' if platform.uname()[4] == 'x86_64' else 'Linux32'

CHROMEDRV_PATH = os.path.join(__base_path, '-'.join((__base_name, __platform_name)))

if not os.path.isfile(CHROMEDRV_PATH):
    raise RuntimeError('This package supports only Linux, MacOSX or Windows platforms')

# little hack to have chrome driver in sys path
os.environ['PATH'] += ':' + CHROMEDRV_PATH
