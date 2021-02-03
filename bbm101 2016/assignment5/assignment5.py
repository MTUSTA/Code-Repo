# Mehmet Taha USTA
import os
import shutil
import string
# Stage1 Step1
class Item:
    def __init__(self, id, title, date, link, genre):
        self.movie_id = int(id)
        self.title = title
        self.date = date
        self.link = link
        self.genre = genre


item_file = open('u.item', 'r')

items = []


def analizing_item(item_line):
    item_line = item_line.rstrip('\n')
    splitted = item_line.split('|')
    items.append(Item(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4:]))
    '''
    print('Movie id\t\t:', splitted[0])
    print('Movie title\t\t:', splitted[1])
    print('Release date\t:', splitted[2])
    print('IMDB Link\t\t:', splitted[3])
    # |0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0 --> adding | at the begin of line -> so i splitted print function
    print('Genre\t\t\t: ', end='')
    print(*splitted[4:], sep="|")
    '''


for i in item_file.readlines():
    analizing_item(i)
    '''print()'''
item_file.close()

genre_file = open('u.genre', 'r')
genre_dict = {}


def create_genre(genre_line):
    genre_line = genre_line.rstrip('\n')
    splitted = genre_line.split('|')
    genre_dict[int(splitted[1])] = splitted[0]


for i in genre_file.readlines():
    create_genre(i)

genre_file.close()

occupation_file = open('u.occupation', 'r')
occupation_dict = {}

def create_occupation(occupation_line):
    occupation_line = occupation_line.rstrip('\n')
    splitted = occupation_line.split('|')
    occupation_dict[splitted[0]] = splitted[1]


for i in occupation_file.readlines():
    create_occupation(i)

occupation_file.close()

user_file = open('u.user', 'r')

class User:
    def __init__(self, user_id, age, gender, occupation, zip_code):
        self.user_id = int(user_id)
        self.age = int(age)
        self.gender = gender
        self.occupation = occupation
        self.zip_code = zip_code


users = []


def create_user(user_line):
    user_line = user_line.rstrip('\n')
    splitted = user_line.split('|')
    users.append(User(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4]))
    '''
    print('User id   :', splitted[0])
    print('User Age  :', splitted[1])
    print('Gender    :', splitted[2])
    print('Occupation:', occupation_dict[splitted[3]])
    print('Zip Code  :', splitted[4])
    '''


for i in user_file.readlines():
    create_user(i)
    '''print()'''

user_file.close()

data_file = open('u.data', 'r')


class Data:
    def __init__(self, user_id, movie_id, rating, timestamp):
        self.user_id = int(user_id)
        self.movie_id = int(movie_id)
        self.rating = int(rating)
        self.timestamp = timestamp


datas = []

for i in data_file.readlines():
    i = i.rstrip("\n")
    i = i.split("\t")
    '''
    print(i)
    '''
    datas.append(Data(i[0], i[1], i[2], i[3]))

data_file.close()

'''# Step2
# film directory
film_directory = os.getcwd() + '\\film'
film_files = os.listdir(film_directory)

for film in film_files:
    opened_file = open(film_directory + '\\' + film, 'r')
    for line in opened_file.readlines():
        line = line.rstrip("\n")
        print(line)'''

# Step 3

review = open('review.txt', 'w', encoding='utf-8')

film_directory = os.getcwd() + '\\film'
film_files = os.listdir(film_directory)
film_folder_names = []
film_folder_review = {}
for i in film_files:
    opened_file = open(film_directory + '\\' + i, 'r')
    name = opened_file.readline().split('(')[0].lower()
    name = name[:-1]
    film_folder_review[name] = opened_file.read()
    film_folder_names.append(name)
items_index = {}
# koda kizmayin pdfdeki istek asiri anormal
for item_num in range(len(items)):
    try:
        splitted_title = items[item_num].title.split('(')
        film_folder_names.index(splitted_title[0].lower())
        items_index[splitted_title[0].lower()] = item_num
        print(items[item_num].movie_id, splitted_title[0] + 'is found in folder', file=review)

    except:
        print(items[item_num].movie_id, splitted_title[0] + 'is not found in folder.', 'Look at ' + items[item_num].link,
              file=review)

review.close()
# Step4

html_code = '''
<!DOCTYPE html>
<html>
<title>{title}</title>
<body>

<font face = "Times New Roman" size = "6" color="red"><b>{title}</b></font>
<p><b>Genre:</b>{Genre_type}</p>
<p><b>IMDB Link:</b><a href={link}>{title}</a></p>
<font face = "Times New Roman" size = "4" color="black"><b>Review:</b></font>
<div>{Review}</div>
<div><br></div>
<div><b>Total User: </b>{total_user}<b> Total Rate:</b> {total_rate}</div>
<div><b>User who rate the film:</b></div>
<p>{user_code}</p>
<div><br></div>
</body>
</html>
'''

User_codes = """
<div><b>User:</b> {user_id} <b>Rate:</b> {user_rate}</div>
<div><b>User Detail:</b> <b>Age:</b> {age} <b>Gender:</b>{gender} <b>Occupation:</b>{occupation} <b>Zip Code:</b> {zip_code}</div>
"""

codes = ""


if os.path.exists('filmList') and os.path.isdir('filmList'):
    shutil.rmtree('filmList')

os.makedirs('filmList')

genre_film_dict = {}
for i in range(len(film_files)):

    saved_item = ""

    for k in items:
        copy_k_title = k.title
        copy_k_title = copy_k_title[:-7]
        if film_folder_names[i] == copy_k_title.lower():
            saved_item = k
            break

    total_user_counter = 0
    point = 0
    for j in datas:
        if saved_item.movie_id == j.movie_id:
            finded_user = ""
            for t in users:
                if j.user_id == t.user_id:
                    finded_user = t
                    total_user_counter += 1
                    break

            codes += User_codes.format(user_id=j.user_id, user_rate=j.rating, age=finded_user.age, gender=finded_user.gender, occupation=occupation_dict[finded_user.occupation], zip_code=finded_user.zip_code)
            point += j.rating
    point /= total_user_counter
    genre_string = ""
    for elem in range(len(saved_item.genre)):
        if saved_item.genre[elem] == "1":
            genre_string += genre_dict[elem] + " "
    genre_string = genre_string[:-1]
    genre_film_dict[film_folder_names[i]] = genre_string
    cap_i = film_folder_names[i].split(" ")
    cap_i = [a.capitalize() for a in cap_i]
    cap_i = " ".join(cap_i)
    file1 = open('filmList/'+film_files[i].split('.')[0]+'.html', 'w')
    print(html_code.format(title=cap_i, Genre_type=genre_string, link=saved_item.link, Review=film_folder_review.get(film_folder_names[i]), total_user=total_user_counter,
                           total_rate=point, user_code=codes), file=file1)
    file1.close()

# Stage 2
print(genre_film_dict)
for i in range(len(film_folder_names)):
    #film_folder_names[i]
    #film_folder_review[film_folder_names[i]]
    pass