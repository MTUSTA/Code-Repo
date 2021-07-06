Create Table Team(
ID:int Primary Key,
City: varchar(100),
Name: varchar(100),
Stadium: varchar(100));

Create Table Player(
num:int Primary Key,
DoB: date,
Name: varchar(100),
StartYear: int,
ShirtNum: int,
TeamID: int
Foreign Key References Team(ID));

Create Table Referee(
ID:int Primary Key,
DoB: date,
Name: varchar(100),
ExpYear: int);

Create Table Match(
HostID:int Foreign Key References Team(ID),
GuestID:int Foreign Key References Team(ID),
Date: date,
Host-score: int,
Guest-score: int,
Primary Key (HostID, GuestID, Date));

Create Table RefereeRole(
HostID:int,
GuestID:int,
Date:date,
RefID:int Foreign Key References Referee(ID),
isMain:Boolean,
Foreign Key (HostID, GuestID, Date) References Match(HostID,GuestID,Date),
Primary Key (HostID, GuestID, Date, RefID));

Create Table Match-Player(
ID:int Primary Key,
PlayerNum:int Foreign Key References Player(num),
MatchDate:date,
HostID:int,
GuestID:int,
numGoals:int,
redFlag:Boolean,
yellowFlag:Boolean,
subID:int Foreign Key References Match-Player(ID),
subTime:int,
Foreign Key (HostID, GuestID, MatchDate) References Match (HostID,GuestID,Date));