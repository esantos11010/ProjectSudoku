/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsudoku;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Ed
 */
public class ProjectSudoku {

    /**
     * @param args the command line arguments
     */
    static GridLayout Grid= new GridLayout(9,9,0,0);
    static JPanel SG=new JPanel(Grid);
    static int counter=0;
    static ArrayList<Integer[]> squares = new ArrayList<>();
    static int [][] solvedpuzzle1=new int[9][9];
    static int [][] notsolvedpuzzle01=new int[9][9];
    static int [][] notsolvedpuzzle02=new int[9][9];
    static int [][] notsolvedpuzzle03=new int[9][9];
    static int [][] notsolvedpuzzle04=new int[9][9];
    static int [][] notsolvedpuzzle05=new int[9][9];
    static int [][] notsolvedpuzzle06=new int[9][9];
    static int [][] notsolvedpuzzle07=new int[9][9];
    static int [][] notsolvedpuzzle08=new int[9][9];
    static int [][] notsolvedpuzzle09=new int[9][9];
    static int [][] notsolvedpuzzle10=new int[9][9];
    static int [][] notsolvedpuzzle11=new int[9][9];
    static int [][] notsolvedpuzzle12=new int[9][9];
    static int [][] notsolvedpuzzle13=new int[9][9];
    static int [][] notsolvedpuzzle14=new int[9][9];
    static int [][] notsolvedpuzzle15=new int[9][9];
    static int [][] notsolvedpuzzle16=new int[9][9];
    static int [][] notsolvedpuzzle17=new int[9][9];
    static int [][] notsolvedpuzzle00=new int[9][9]; //used for proposed removals
    //static int BoxSize=80;
    static int top=1, bot=1, left=1, right=1;
    static Font font1 = new Font("ARIEL", Font.BOLD, 40);
    static Font font2 = new Font("MONOSPACED", Font.BOLD, 14);
    static JTextField[][] boxes=new JTextField [9][9];
    static JPanel Main=new JPanel(new BorderLayout());
    static ArrayList<Integer> available=new ArrayList<>();
    static ArrayList<Integer> U=new ArrayList<>();
    
    public static void Check(){
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(!boxes[i][j].getText().isEmpty()){
                    int x=Integer.parseInt(boxes[i][j].getText());
                    if(x!=solvedpuzzle1[i][j]){
                        JTextField temp=boxes[i][j];
                        temp.setBackground(Color.red);
                        temp.addKeyListener(new KeyAdapter(){
                            public void keyReleased(KeyEvent e){
                                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                                    temp.setBackground(Color.white);
                                }
                            }
                        });
                        boxes[i][j]=temp;
                    }
                }
            }
        }
    }
    //this is used to check if a sudoku has been completed and then provide a win screen
    public static void Check2(){
        boolean finished=true;
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(boxes[i][j].getText().isEmpty()){
                    finished=false;
                    break;
                }
                else{
                    int x=Integer.parseInt(boxes[i][j].getText());
                    if(x!=solvedpuzzle1[i][j]){
                        finished=false;
                    }
                }
            }
        }
        if(finished){
            JOptionPane.showMessageDialog(null, "SOLVED!", "title", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void LoadSudokuSolution(int[][] P, int[][]SP) {
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                    SG.remove(boxes[i][j]);
            }
        }
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(P[i][j]==0){
                    boxes[i][j]=new JTextField(SP[i][j]+"");
                    boxes[i][j].setEditable(false);
            }
                else{
                    boxes[i][j]=new JTextField(P[i][j]+"");
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.CYAN);
                }
            SG.add(boxes[i][j]);
            boxes[i][j].setFont(font1);
            if(j==2||j==5){
                right=4;
            }
            else{
                right=1;
            }
            if(i==2||i==5){
                bot=4;
            }
            else{
                bot=1;
            }
            boxes[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bot, right, Color.black));
            boxes[i][j].setHorizontalAlignment(JTextField.CENTER);
            boxes[i][j].setAlignmentX(JTextField.CENTER_ALIGNMENT);
            }
        }
    }

    public static void LoadSudoku(int[][] P) {
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                SG.remove(boxes[i][j]);
            }
        }
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(P[i][j]==0){
                    boxes[i][j]=new JTextField();
                    boxes[i][j].setDocument(new LengthRestrictedDocument(1));
                    boxes[i][j].addKeyListener(new KeyAdapter(){//
                        public void keyTyped(KeyEvent e){
                            char input=e.getKeyChar();
                            if((input<'1'|| input>'9') && input!='\b'){
                                e.consume();
                            }
                        }
                    });
                    boxes[i][j].getDocument().addDocumentListener(new DocumentListener() {
                        public void changedUpdate(DocumentEvent e){}
                        public void removeUpdate(DocumentEvent e){}
                        public void insertUpdate(DocumentEvent e){
                            Check2();
                        }

                    });
            }
                else{
                    boxes[i][j]=new JTextField(P[i][j]+"");
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.CYAN);
                }
            SG.add(boxes[i][j]);
            boxes[i][j].setFont(font1);
            if(j==2||j==5){
                right=5;
            }
            else{
                right=2;
            }
            if(i==2||i==5){
                bot=5;
            }
            else{
                bot=2;
            }
            boxes[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bot, right, Color.black));
            boxes[i][j].setHorizontalAlignment(JTextField.CENTER);
            boxes[i][j].setAlignmentX(JTextField.CENTER_ALIGNMENT);
            }
        }
    }
    
    public static void Create(){
        available=numberHolder();
        solvedpuzzle1 = new int[9][9];
        for(int row=0; row<3;row++){
            for(int col=0;col<3;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
            }
        }
        available=numberHolder();
        for(int row=3; row<6;row++){
            for(int col=3;col<6;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
            }
        }
        available=numberHolder();
        for(int row=6; row<9;row++){
            for(int col=6;col<9;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
            }
        }
        SudokuSolver.Solve1(solvedpuzzle1);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                notsolvedpuzzle01[i][j]=solvedpuzzle1[i][j];
                notsolvedpuzzle00[i][j]=solvedpuzzle1[i][j];
            }
        }
        counter=0;
        //ArrayList<Integer[]> squares = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                squares.add(new Integer[]{i, j});
            }
        }


        //REDO ALL OF THIS SH*T
        while(counter<56){
            //System.out.println(counter);
            removeDiagonal();
            countTheEmptySquares();
        }
        System.out.println("NEW FUNCTION HERE:");

        SudokuSolver.Solutions(notsolvedpuzzle00);
        System.out.println("DONE!");
        LoadSudoku(notsolvedpuzzle01);
    }
    //redo this, start by creating a list of all squares, remove one at random check for solution then remove from list
    
    public static void removeDiagonal(){
        Integer[] pair=squares.get((int)(Math.random() * (squares.size())));
        int x = pair[0];
        int y = pair[1];
        //System.out.println(x+" "+y);
        notsolvedpuzzle00[x][y]=0;
        
        if(stillSameSolution()){

            notsolvedpuzzle01[x][y]=0;
            squares.remove((Object)pair);
        }
        else{
            notsolvedpuzzle00[x][y]=notsolvedpuzzle01[x][y];
        }
    }

    public static boolean stillSameSolution(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                notsolvedpuzzle02[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle03[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle04[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle05[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle06[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle07[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle08[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle09[i][j]=notsolvedpuzzle00[i][j];
                notsolvedpuzzle10[i][j]=notsolvedpuzzle00[i][j];
            }
        }
        SudokuSolver.Solve2(notsolvedpuzzle02);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle02[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve3(notsolvedpuzzle03);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle03[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve4(notsolvedpuzzle04);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle04[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve5(notsolvedpuzzle05);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle05[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve6(notsolvedpuzzle06);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle06[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve7(notsolvedpuzzle07);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle07[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve8(notsolvedpuzzle08);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle08[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve9(notsolvedpuzzle09);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle09[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve10(notsolvedpuzzle10);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle10[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void countTheEmptySquares(){
        counter=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (notsolvedpuzzle01[i][j]==0) {
                    counter++;
                }
            }
        }
    }
    public static ArrayList<Integer> numberHolder(){
        ArrayList<Integer> hello=new ArrayList<>();
        hello.add(1);
        hello.add(2);
        hello.add(3);
        hello.add(4);
        hello.add(5);
        hello.add(6);
        hello.add(7);
        hello.add(8);
        hello.add(9);
        return hello;
    }
    
    public static void LoadHelp(){
        JFrame Help=new JFrame("Sudoku Rules");
        Help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Help.setPreferredSize(new Dimension(400,180));
        JTextArea Instructions=new JTextArea();
        Instructions.setFont(font2);
        String s= "Sudoku is played on a grid of 9 x 9 spaces. \n"
                + "Within the rows and columns are 9 “squares” \n"
                + "(made up of 3 x 3 spaces). Each row, column \nand"
                + " square (9 spaces each) needs to be \nfilled out with "
                + "the numbers 1-9, without \nrepeating any numbers within"
                + " the row,\ncolumn or square. ";
        Instructions.setText(s);
        Instructions.setColumns(75);
        Instructions.setEditable(false);
        Instructions.setBackground(Color.white);
        Main.add(Instructions, BorderLayout.WEST);
        Main.revalidate();
        Help.add(Instructions);
        Help.pack();
        Help.setLocationRelativeTo(null);
        Help.setVisible(true);
    }
    
    static public final class LengthRestrictedDocument extends PlainDocument {//taken from stackoverflow $$ https://stackoverflow.com/questions/33751343/java-jtextfield-length-and-filter

    private final int limit;

    public LengthRestrictedDocument(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
        throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offs, str, a);
            }
        }
    }

    
    
    public static String toStrings(int[][] s1){
        
        String s="";
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(s1[i][j]==notsolvedpuzzle01[i][j]){
                    s+=" "+s1[i][j]+" ";
                }
                else{
                    s+="?"+s1[i][j]+"?";
                }
            }
            s+="\n";
        }
        s+="\n";
        try {
            FileWriter myWriter = new FileWriter("file"+counter+".txt");
            myWriter.write(s);
            myWriter.close();
            counter++;
            //System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return s;
    }

public static void main(String[] args) {
        // TODO Create a function for the window of the app to go in
        // TODO remake the remove fuction, currently there are puzzles with multiple solutions being passed
        // TODO remake create
        JFrame frame=new JFrame("Project:Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar MB=new JMenuBar();
        JMenu M=new JMenu("Menu");
        JMenuItem MI1, MI2, MI3, MI4;
        MI1=new JMenuItem("Start", KeyEvent.VK_S);
        MI2=new JMenuItem("Instuctions", KeyEvent.VK_H);
        MI3=new JMenuItem("Check", KeyEvent.VK_C);
        MI4=new JMenuItem("SOLVE");
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                boxes[i][j]=new JTextField();
                boxes[i][j].setEditable(false);
                boxes[i][j].setBackground(Color.white);
                if(j==2||j==5){
                    right=4;
                }
                else{
                    right=1;
                }
                if(i==2||i==5){
                    bot=4;
                }
                else{
                    bot=1;
                }
                boxes[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bot, right, Color.black));
                SG.add(boxes[i][j]);
            }
        }
        Main.add(SG);
        MI1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                Create();
                Main.add(SG, BorderLayout.CENTER);
                Main.revalidate();
            }
        });
        MI2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                LoadHelp();
            }
        });
        MI3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                Check();
                Main.revalidate();
                
            }
        });
        MI4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                LoadSudokuSolution(notsolvedpuzzle01, solvedpuzzle1);
                Main.revalidate();
                
            }
        });
        M.add(MI1);
        M.add(MI2);
        M.add(MI3);
        M.add(MI4);
        Main.add(SG);
        MB.add(M);
        MB.setVisible(true);
        M.setVisible(true);
        frame.add(MB, BorderLayout.NORTH);
        frame.add(Main);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700,700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}