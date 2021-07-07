#include <iostream>
#include <fstream>
#include <string>

#include <algorithm>
#include <sstream>

using namespace std;

class suit{
    public:
        char type;
        suit(char type){
            this->type = type;
        }
};

class card{
    public:
        suit* card_suit;
        string number = "";
        // 1 is close, 0 is open
        int closed;
        card(char type,string number,int closed){
            this->card_suit = new suit(type);
            this->number = number;
            this->closed = closed;
        }
        //dummy constructer
        card(){
        }
};

class pile{
    public:
        int pile_counter;
        card* cards;
        pile(int counter){
            this->pile_counter = counter;
        }
        // dummy constructer
        pile(){}
};


class waste{
    public:
        card cards[3];
};

class stock{
    public:
        //int max_s_counter = 3;
        int s_counter = 0;
        int opened = 0;
        waste stock_waste;
};
class tableauArea{
    public:
        pile piles[7];
};
class foundation{
    public:
        int counter = 0;
        char type;
        card cards[13];
        foundation(char type){
            this->type = type;
        }
        // dummy constructer
        foundation(){}

};
class foundationArea{
    public:
        foundation foundations[4];
};

class GameBoard{
    public:
        int stock_limit = 0;
        int stock_counter = 8;
        // 24 cards for stocks
        stock* g_stocks;
        // 28 cards for piles
        tableauArea* g_tableArea;
        waste* g_wastes;
        foundationArea* g_foundArea;
        GameBoard(){

            this->g_stocks = new stock[8];
            this->g_tableArea = new tableauArea();
            this->g_wastes = new waste();
            this->g_foundArea = new foundationArea();
            // at the start of the game waste = _ _ _

            // dynamic memory allocation for waste
            for(int i = 0; i<3;i++){
                this->g_wastes->cards[i] = card('_',"___",0);
            }

            // dynamic memory allocation for pile
            for(int p = 0;p<7;p++){
                this->g_tableArea->piles[p] = pile(p+1);
                // why 19 -> because sample IO 3 -> line 566-> 1 pile 14 element -> max possible 19
                this->g_tableArea->piles[p].cards = new card[19];
                // filling empty elements for good printing for piles
                for(int j = 0;j<19;j++){
                    this->g_tableArea->piles[p].cards[j] = card(' ',"   ",0);
                }

            }
            // create foundation area
            string types = "HDSC";
            for(int f=0;f<4;f++){
                this -> g_foundArea->foundations[f] = foundation(types[f]);

                for(int c = 0 ; c<13 ; c++){
                    //
                    this->g_foundArea->foundations[f].cards[c] = card('_',"___",0);
                }
            }
        }

        //foundation game_foundation;
    void fill_stock(string card_number,int number){
        char type = card_number.at(0);
        // closed = 0 means -> cards is open
        this->g_stocks[number/3].stock_waste.cards[number % 3] = card(type,card_number,0);

    }
    // filling pile at the start of the game
    void fill_pile(string card_number,int x,int y, int closed){
        char type = card_number.at(0);
        this->g_tableArea->piles[x].cards[y]= card(type,card_number,closed);
    }
    // printing solitaire table
    void print_game(ofstream* output){
            if(this->stock_counter == this->stock_limit){
                *output << endl << "___ " + this->g_wastes->cards[2].number + " " + this->g_wastes->cards[1].number + " " +this->g_wastes->cards[0].number;
            }else if(this->stock_counter == this->stock_limit-1) {
                *output << endl << "@@@ ___ ___ ___" ;

            }else{
                *output << endl << "@@@ " + this->g_wastes->cards[2].number + " " + this->g_wastes->cards[1].number + " " +this->g_wastes->cards[0].number;
            }

        // 9 space
        *output << "         " ;
        for(int f=0;f<4;f++){
            if(this->g_foundArea->foundations[f].counter == 0){
               *output << this->g_foundArea->foundations[f].cards[this->g_foundArea->foundations[f].counter].number;
               if(f<3){
                   *output << " ";
               }
            }
            else{
                *output << this->g_foundArea->foundations[f].cards[this->g_foundArea->foundations[f].counter-1].number;
                if(f<3){
                    *output << " ";
                }
            }
        }

        *output << endl << endl;
        // get max pile counter for correct piles printing
        int max_pile_counter = 0;
        for(int i = 0 ; i<7 ; i++){
            if(this->g_tableArea->piles[i].pile_counter>max_pile_counter){
                max_pile_counter = this->g_tableArea->piles[i].pile_counter;
            }
        }

        for(int y = 0; y < max_pile_counter ; y++){
            for(int x = 0; x < 7 ; x++){
                if(this->g_tableArea->piles[x].cards[y].closed == 0){
                    *output << this->g_tableArea->piles[x].cards[y].number+"   ";;
                }
                else if(this->g_tableArea->piles[x].cards[y].closed == 1){
                  *output << "@@@   ";;
                }
            }
            *output<< endl;
        }
        *output<< endl;
    }

    // edit stock cards and creating new stock deck
    void create_new_stock(){
            int stopper = 0;
            for(int i = 7 ; i>-1 ; i--){
                //this->g_stocks[i].max_s_counter = 0;
                for(int j = 2; j>-1; j--){
                    // if area is empty
                    if(this->g_stocks[i].stock_waste.cards[j].number == "___"){
                        // find card
                        int i2,j2=j,early_stop = 0;
                        for( i2=i ; i2 > -1 ; i2--){
                            for(; j2 > -1 ; j2--){
                                // if area is empty, slide card at the avaible area
                                if(this->g_stocks[i2].stock_waste.cards[j2].number != "___"){
                                    // card slided
                                    this->g_stocks[i].stock_waste.cards[j] = this->g_stocks[i2].stock_waste.cards[j2];
                                    this->g_stocks[i].s_counter--;
                                    //this->g_stocks[i].max_s_counter++;
                                    // delete old value
                                    this->g_stocks[i2].stock_waste.cards[j2] = card(' ',"___",0);
                                    this->g_stocks[i2].s_counter++;
                                    // stop loop
                                    early_stop = 1;
                                    // ignore empty area
                                    this->stock_limit = i;
                                    break;
                                }

                            }
                            // at the end
                            if(i2!=0 && j2 == -1){
                                j2 = 2;

                            }
                            if(early_stop){
                                break;
                            }
                        }
                        // reset sliding, gain time
                        if(i2==-1 && j2 == -1){
                            stopper = 1;
                            // set limit again

                        }
                    }
                    if(stopper){
                        break;
                    }
                }
                this->g_stocks[i].opened = 0;
                if(stopper){
                    break;
                }

            }
        }
    // open stock cars
    void open_from_stock(){
            // at the start of deck setting
            if(this->stock_counter == this->stock_limit){
                // setting @@@ ___ ___ ___
                this->stock_counter--;

            }
            // end of the stock -> create new stock and set values
            else if(this->stock_counter == this->stock_limit-1){
                // reset stock -> create new stcok group
                this->stock_counter = 7;
                this->create_new_stock();
                this->g_stocks[this->stock_counter].opened = 1;
                for (int i = 0; i < 3; i++) {
                    this->g_wastes->cards[i] = this->g_stocks[this->stock_counter].stock_waste.cards[i];
                }
            }
            // changing stock cards
            else {
                this->stock_counter--;
                while (this->g_stocks[this->stock_counter].opened == 2) {
                    this->stock_counter--;
                }
                this->g_stocks[this->stock_counter].opened = 1;
                for (int i = 0; i < 3; i++) {
                    this->g_wastes->cards[i] = this->g_stocks[this->stock_counter].stock_waste.cards[i];
                }
            }
    }
    // found index in foundation by type -> helper function
    int found_index(char type){
        for(int i = 0; i<4 ;i++){
            if(this->g_foundArea->foundations[i].type == type){
                return i;
            }
        }
        return -1;
    }
    // move cards from waste or pile to foundation
    void move_to_foundation(string word, ofstream* output){
        if (word == "waste"){
            // find foundation by char(type)
            int index = this->found_index(this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter].card_suit->type);

            int waste_num = stoi(this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter].number.substr(1));
            int found_num = 0;
            if(this->g_foundArea->foundations[index].counter != 0 && this->g_foundArea->foundations[index].cards[this->g_foundArea->foundations[index].counter].number == "___"){
                found_num = stoi(this->g_foundArea->foundations[index].cards[this->g_foundArea->foundations[index].counter-1].number.substr(1));
            }
            // Invalid Move! -> 2 card number difference more than 1
            if(waste_num - found_num != 1){
                *output <<endl<< "Invalid Move!" << endl;
                return;
            }

            // move card from waste to foundation
            this->g_foundArea->foundations[index].cards[this->g_foundArea->foundations[index].counter] = this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter];
            this->g_foundArea->foundations[index].counter++;

            // delete card from waste
            this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter] = card(' ',"___",0);
            this->g_stocks[this->stock_counter].stock_waste.cards[this->g_stocks[this->stock_counter].s_counter] = card(' ',"___",0);
            this->g_stocks[this->stock_counter].s_counter++;

        }
        // move to foundation pile
        else{
            int num = stoi(word);
            // find index of suit
            if(this->g_tableArea->piles[num].pile_counter == 0){
                *output <<endl<< "Invalid Move!" << endl;
                return;
            }

            int index = this->found_index(this->g_tableArea->piles[num].cards[this->g_tableArea->piles[num].pile_counter -1].card_suit->type);
            int found_number = 0;
            int pile_number = stoi(this->g_tableArea->piles[num].cards[this->g_tableArea->piles[num].pile_counter -1].number.substr(1));
            if(this->g_foundArea->foundations[index].counter>0){
                found_number = stoi(this->g_foundArea->foundations[index].cards[this->g_foundArea->foundations[index].counter-1].number.substr(1));
            }
            // checking card difference C13 , H10 --> if 13-10 != 1 -> invalid move
            if(pile_number-found_number != 1) {
                *output << endl << "Invalid Move!" << endl;
                return;
            }
            // adding card from pile to foundation
            this->g_foundArea->foundations[index].cards[this->g_foundArea->foundations[index].counter] = this->g_tableArea->piles[num].cards[ this->g_tableArea->piles[num].pile_counter - 1];
            this->g_foundArea->foundations[index].counter++;
            // delete card from pile
            this->g_tableArea->piles[num].cards[this->g_tableArea->piles[num].pile_counter - 1] = card(' ', "   ",0);
            this->g_tableArea->piles[num].pile_counter--;

        }

    }

    void move_pile(string source_pile, string source_card ,string destination_pile,ofstream* output){
            //convert value -> string to int
        int s_pile = stoi(source_pile);
        int s_card = stoi(source_card);
        int d_pile = stoi(destination_pile);

        // if source pile card is closed -> Invalid Move!
        if(this->g_tableArea->piles[s_pile].cards[this->g_tableArea->piles[s_pile].pile_counter -(s_card+1)].closed == 1){
            *output <<endl<< "Invalid Move!" << endl;
            return;
        }
        int s_pile_num = stoi(this->g_tableArea->piles[s_pile].cards[this->g_tableArea->piles[s_pile].pile_counter -(s_card+1)].number.substr(1));
        // if card is not 13 , Invalid Move!
        if(this->g_tableArea->piles[d_pile].pile_counter == 0){
            if (s_pile_num != 13){
                *output <<endl<< "Invalid Move!" << endl;
                return;
            }
        }
        // if target pile card is closed -> Invalid Move!
        if(this->g_tableArea->piles[d_pile].cards[this->g_tableArea->piles[d_pile].pile_counter-1].closed == 1) {
            *output << endl << "Invalid Move!" << endl;
            return;
        }
        // if destination pile is empty , not change . value is 0
        int d_pile_num = 0;
        // if pile is not empty, set cards value
        if (this->g_tableArea->piles[d_pile].pile_counter != 0){
            d_pile_num = stoi(this->g_tableArea->piles[d_pile].cards[this->g_tableArea->piles[d_pile].pile_counter -1].number.substr(1));
        }
        // Invalid Move! -> 2 card number difference more than 1
        if ( d_pile_num != 0 && d_pile_num - s_pile_num != 1){
            *output <<endl<< "Invalid Move!" << endl;
            return;
         }
        else{
            //
            int limit = this->g_tableArea->piles[s_pile].pile_counter;
            for (int i = this->g_tableArea->piles[s_pile].pile_counter - (s_card+1) ; i <  limit; i++){
                //increase destination counter
                this->g_tableArea->piles[d_pile].pile_counter++;
                // adding cart destination pile
                this->g_tableArea->piles[d_pile].cards[this->g_tableArea->piles[d_pile].pile_counter-1] = this->g_tableArea->piles[s_pile].cards[i];
                // remove card from source pile
                this->g_tableArea->piles[s_pile].cards[i] = card(' ',"   ",0);
                this->g_tableArea->piles[s_pile].pile_counter--;
            }
        }
    }

    int opened_index(){
            int copy_stock_counter = this -> stock_counter;
            while (copy_stock_counter < 8){
                copy_stock_counter++;
                if(this->g_stocks[copy_stock_counter].opened == 1 && this->g_stocks[copy_stock_counter].s_counter<3){
                    return copy_stock_counter;
                }
            }
            return this -> stock_counter;
        }

    void move_waste(string waste_num,ofstream* output){
            // string to int
            int source_num = stoi(waste_num);
            // string to int
            int source_waste_num = stoi(this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter].number.substr(1));

            if(this->g_tableArea->piles[source_num].pile_counter != 0){

                int destination_pile_num = stoi(this->g_tableArea->piles[source_num].cards[this->g_tableArea->piles[source_num].pile_counter-1].number.substr(1));
                // Invalid Move!
                if(destination_pile_num-source_waste_num !=1){
                    *output << endl << "Invalid Move!" <<endl;
                    return;
                }

            }
            // if card is not 13 , Invalid Move!
            if(this->g_tableArea->piles[source_num].pile_counter == 0 && source_waste_num != 13) {
                *output << endl << "Invalid Move!" << endl;
                return;
            }
            //adding card on the tablo on pile(source num)
            this->g_tableArea->piles[source_num].pile_counter++;
            this->g_tableArea->piles[source_num].cards[this->g_tableArea->piles[source_num].pile_counter-1]= this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter];
            // deleting card on stock(waste)
            this->g_wastes->cards[this->g_stocks[this->stock_counter].s_counter] = card('_',"___",0);
            this->g_stocks[this->stock_counter].stock_waste.cards[this->g_stocks[this->stock_counter].s_counter]= card('_',"___",0);
            this->g_stocks[this->stock_counter].s_counter++;
            // if stock has no card -> set value 2 -> stock finished
            if(this->g_stocks[this->stock_counter].s_counter == 3) {
                this->g_stocks[this->stock_counter].opened = 2;
                int result = this->opened_index();
                if (result != this->stock_counter) {
                    if (this->stock_counter == this->stock_limit) {
                        this->stock_limit++;
                    }
                    this->stock_counter = result;
                    // set card last element
                    this->g_wastes->cards[2] = this->g_stocks[this->stock_counter].stock_waste.cards[this->g_stocks[this->stock_counter].s_counter];


                }
            }

    }

    // if pile is close, open it
    void open_pile(string number,ofstream* output){
        int num = stoi(number);
        // if card is closed -> give an error -> invalid move
        if(this->g_tableArea->piles[num].cards[this->g_tableArea->piles[num].pile_counter-1].closed == 0){
            //Invalid Move!
            *output <<endl << "Invalid Move!" << endl;
            return;

        }
        this->g_tableArea->piles[num].cards[this->g_tableArea->piles[num].pile_counter - 1].closed = 0;


    }
    void exit(ofstream* output){
        *output <<endl << "Game Over!" << endl;
    }
    // if all cards 13 in foundations -> finish
    void check_finish(ofstream* output){
        int check = 0;
        for(int i = 0; i<4 ;i++) {
            if (this->g_foundArea->foundations[i].counter == 13) {
                check++;
            }
        }
        // if all foundations are 13 -> win game
        if(check == 4){
            *output << endl  << "****************************************" << endl << endl;
            *output << "You Win!" << endl << endl;
            *output << "Game Over!" << endl;
        }
    }
};
// creating stock and pile
void create_deck(string file_name,GameBoard* game){
    string line;
    // Read from the text file
    ifstream deck(file_name);
    // Use a while loop together with the getline() function to read the file line by line
    int counter = 0, x = 6, y = 6, place = 1,reset = 1,closed=1;
    while (getline (deck, line)) {
      // Output the text from the file
      if(counter < 24){
          game->fill_stock(line,counter);
      }
      else{
        place--;
        // if card is first element of pile , card will be open
        if(place == 0){
            closed = 0;
        }
        // adding cards in to pile
        game->fill_pile(line,x,y,closed);
        x--;

        if(place == 0){
            y--;
            x = 6;
            reset++;
            place = reset;
            closed = 1;
        }
      }
      // count cards
      counter++;
    }
    // Close the file
    deck.close();
}
// split sentence to words
string* split(string st,int space){
    string* words = new string[space+1];
    string word = "";
    int counter =0;
    for ( char s : st){
        if (s== ' '){
            words[counter]= word;
            counter ++;
            word = "";
        }
        else{
            word = word + s;
        }
    }
    words[counter]= word;
    return words;
}

// read command txt and apply command on gameboard
void apply_commands(string file_name,GameBoard* game,ofstream* output){
    string line;
    // Read from the text file
    ifstream command(file_name);

    string* words = new string[5];
    // Use a while loop together with the getline() function to read the file line by line
    while (getline (command, line)) {
        // delete new line character
        line.erase(std::remove(line.begin(), line.end(), '\n'), line.end());
        // count space
        int spaces = std::count_if(line.begin(), line.end(),[](unsigned char c){ return std::isspace(c); });
        // split string
        string* splitted_line = split(line,spaces);
        *output << line << endl;

        // open from stock
        if(splitted_line[0] == "open" && splitted_line[1] == "from" && splitted_line[2] == "stock"){
            game->open_from_stock();
        }
            // move to foundation [ pile <pile_num> ] [ waste ]
        else if(splitted_line[0] == "move" && splitted_line[1] ==  "to" && splitted_line[2] ==  "foundation"){
            game->move_to_foundation(splitted_line[spaces], output);
        }
            // move [ pile <source_pile_num> <source_pile_card_index> ] <destination_pile_num> [ waste ] <destination_pile_num>[ foundation <source_foundation_num> ] <destination_pile_num>
        else if(splitted_line[0] == "move" && splitted_line[1] != "to"){
            if(splitted_line[1] == "waste"){
                game->move_waste(splitted_line[2],output);
            }
            else if(splitted_line[1] == "pile"){
                game->move_pile(splitted_line[2],splitted_line[3],splitted_line[4], output);
            }
        }
            // open <pile_num>
        else if(splitted_line[0] == "open" && splitted_line[1] != "from"){
            game->open_pile(splitted_line[spaces], output);
        }
            // exit
        else if(splitted_line[0] == "exit"){
            *output << endl<<"****************************************"<<endl;
            game->exit(output);

            break;
        }
      *output << endl<<"****************************************"<<endl;
      game->print_game(output);
    }
    // Close the file
    command.close();
}

// free -> dynamic allocated memory
void allmemory_free(GameBoard* game){
    delete game->g_wastes;
    // dynamic memory allocation for pile
    for(int p = 0;p<7;p++){
        // filling empty elements for good printing for piles
        for(int j = 0;j<19;j++){
            delete game->g_tableArea->piles[p].cards[j].card_suit;
        }

    }
    delete game->g_tableArea;
    delete game->g_foundArea;
}

int main(int argc, char** argv){
    //Create Game object
    GameBoard* game = new GameBoard();
    // Create and open a text file
    ofstream output(argv[3]);
    // creating deck
    create_deck(argv[1],game);
    //printing game first look
    game->print_game(&output);
    // apply commands.txt lines
    apply_commands(argv[2],game, &output);
    // if game is finish succesfull, write (You Win! Game Over!) on output
    game->check_finish(&output);
    // Close the file
    output.close();
    //deallocate memory
    allmemory_free(game);
    delete game;

    return 0;
}
