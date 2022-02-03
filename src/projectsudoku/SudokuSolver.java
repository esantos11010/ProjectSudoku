package projectsudoku;

import java.util.ArrayList;

public class SudokuSolver {
    //starts at top left, moves across
    public static boolean Solve1(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=0;row<9;row++){
                for(int col=0;col<9;col++){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //starts at bottom right, moves up
    public static boolean Solve2(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int col=8;col>-1;col--){
                for(int row=8;row>-1;row--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(SudokuSolver.checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(SudokuSolver.Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //starts at top left, moves down
    public static boolean Solve3(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int col=0;col<9;col++){
                for(int row=0;row<9;row++){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //starts at bottom right moves across
    public static boolean Solve4(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=8;row>-1;row--){
                for(int col=8;col>-1;col--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(SudokuSolver.checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(SudokuSolver.Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //This is solve1 but starting the try at nine and working down to one instead
    public static boolean Solve5(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=0;row<9;row++){
                for(int col=0;col<9;col++){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=9; n>=1;n--){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //This is solve2 but starting the try at nine and working down to one instead
    public static boolean Solve6(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int col=8;col>-1;col--){
                for(int row=8;row>-1;row--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=9; n>=1;n--){
                if(SudokuSolver.checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(SudokuSolver.Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }

    //This is solve3 but starting the try at nine and working down to one instead
    public static boolean Solve7(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int col=0;col<9;col++){
                for(int row=0;row<9;row++){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=9; n>=1;n--){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //This is solve4 but starting the try at nine and working down to one instead
    public static boolean Solve8(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=8;row>-1;row--){
                for(int col=8;col>-1;col--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=9; n>=1;n--){
                if(SudokuSolver.checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(SudokuSolver.Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }
    //starts at top right moves across
    public static boolean Solve9(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=0;row<9;row++){
                for(int col=8;col>-1;col--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }

    public static boolean Solve10(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int col=0;col<9;col++){
                for(int row=8;row>-1;row--){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }

    public static boolean Solve000000000(int[][] Sudoku){
        boolean solved=true;
        int x=0, y=0;
            for(int row=0;row<9;row++){
                for(int col=0;col<9;col++){
                    if(Sudoku[row][col]==0){
                        x=row;
                        y=col;
                        solved=false;
                        break;
                    }
                }
                if(!solved){
                    break;
                }
            }
            if(solved){
                return true;
            }
            for(int n=1; n<=9;n++){
                if(checkAvailable(Sudoku, x, y, n)){
                    Sudoku[x][y]=n;
                    if(Solve1(Sudoku)){
                        return true;
                    }
                    else{
                        Sudoku[x][y]=0;
                    }
                }
            }
        return false;
    }

    public static boolean checkAvailable(int[][] S,int row, int col, int n){
        //U.clear();
        ArrayList<Integer> U=new ArrayList<>();
        U.add(1);
        U.add(2);
        U.add(3);
        U.add(4);
        U.add(5);
        U.add(6);
        U.add(7);
        U.add(8);
        U.add(9);
        int i;
        for(int x=0;x<9; x++){   
            i=S[x][col];
            if(i!=0){
                if(U.contains(i)){
                    U.remove((Object)i);
                }
            }
        }
        for(int y=0; y<9; y++){
            i=S[row][y];
            if(i!=0){
                if(U.contains(i)){
                    U.remove((Object)i);
                }
            }
        }
        int boxlimit1=row/3;
        int boxlimit2=col/3;
        for(int r=boxlimit1*3;r<(boxlimit1*3)+3;r++){
            for(int c=boxlimit2*3;c<(boxlimit2*3)+3;c++){
                i=S[r][c];
                if(U.contains(i)){
                    U.remove((Object)i);
                }
            }
        }
        if(U.contains(n)){
            return true;
        }
        return false;
    }
}
