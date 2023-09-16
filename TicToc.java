  import java.util.Scanner;
  import java.util.Random;
  class TicTocToe{
    static char[][] board;

    public TicTocToe(){
        board= new char[3][3];
        init();
    }
     void init(){
        for(int i=0;i<board.length;i++){
            for(int j=0; j<board[i].length;j++){
                board[i][j]= ' ';
            }
        }
    }
    static void displayBoard(){
        System.out.println("-------------");
        for(int i=0; i<board.length;i++){
            System.out.print("| ");
            for(int j=0; j<board[i].length;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
     static  void markPlace(int row, int col, char mark){
        if(row<=2 && row>=0 && col<=2 && col>=0){
            board[row][col]= mark;
        }
        else{ System.out.println("In valid position");}
    }
     static boolean rowWin(){
        for(int j=0; j<board.length;j++){
            if(board[j][0]!=' ' && board[j][0]==board[j][1] && board[j][1]==board[j][2]){
                return true;
            }
        }
        return false;
    }

    public static boolean columnWin (){
        for(int i=0; i<board.length; i++){
            if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i]){
                return true;
            }
        }
        return false;
    }

     static boolean diagWin(){
        if(board[0][0]!= ' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!= ' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            return true;
        }
        return false;
    }
     static boolean isDraw(){
        for(int i=0;i<3;i++){
            for( int j=0; j<3; j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}

    

//  ---------------------------------------------------------------------------------
   public class TicToc{
    public static void main(String[] args){
        TicTocToe t = new TicTocToe();
       

       HumanPlayer p1 = new HumanPlayer("You", 'X');
       AIPlayer p2 = new AIPlayer("AI-Player", 'O');

       PlayGame cp ;
       cp =p1;

       while(true){
        System.out.println(cp.name+" turn");
       cp.makeMove();
       TicTocToe.displayBoard();
       if(TicTocToe.columnWin() || TicTocToe.rowWin() || TicTocToe.diagWin()){
        System.out.println(cp.name+ " has Won! ");
        break;
       }
       else
        if(TicTocToe.isDraw()){
        System.out.println("match Draw");
        break;
       }

       else{
        if(cp == p1){
            cp=p2;
        }
        else{
            cp = p1;
        }
       }
       }
        
    }
   }

abstract class PlayGame{
    String name;
    char mark;
    abstract void makeMove();

    boolean isValidMove(int row, int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTocToe.board[row][col]==' '){
            return true;
        }     
    }
    return false;
    }

}

class AIPlayer extends PlayGame{
    AIPlayer(String name, char mark){
        this.name = name;
        this.mark= mark;

    }
    void makeMove(){
        
        int row;
        int col;
        do{
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        }while(!isValidMove(row,col));

        TicTocToe.markPlace(row,col,mark);
    }
}



class HumanPlayer extends PlayGame{
    
     HumanPlayer(String name, char mark){
        this.name= name;
        this.mark= mark;
    }

    void makeMove(){
        Scanner sc = new Scanner(System.in);
        
        int row;
        int col;
        do{
            System.out.println("Enter row and column ");
            row = sc.nextInt();
            col = sc.nextInt();
        }while(!isValidMove(row,col));

        TicTocToe.markPlace(row,col,mark);

        }   
}