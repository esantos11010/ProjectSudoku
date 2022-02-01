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
//import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//import jdk.tools.jmod.Main;

/**
 *
 * @author Work
 */
public class ProjectSudoku {

    /**
     * @param args the command line arguments
     */
    static GridLayout Grid= new GridLayout(9,9,0,0);
    static JPanel SG=new JPanel(Grid);
    static int [][] solvedpuzzle1=new int[9][9];
    static int [][] solvedpuzzle2=new int[9][9];
    static int [][] notsolvedpuzzle1=new int[9][9];
    static int [][] notsolvedpuzzle2=new int[9][9];
    static int [][] notsolvedpuzzle3=new int[9][9];
    static int [][] notsolvedpuzzle4=new int[9][9];
    static int [][] notsolvedpuzzle5=new int[9][9];
    static int BoxSize=80;
    static int top=1, bot=1, left=1, right=1;
    //static Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    //static Border rightBorder = BorderFactory.createMatteBorder(1, 1, 1, 4, Color.black);
    static Font font1 = new Font("ARIEL", Font.BOLD, 40);
    static Font font2 = new Font("MONOSPACED", Font.BOLD, 14);
    //static Dimension GridSize=new Dimension(BoxSize*10,BoxSize*9+40);// must add to make roome for menu bar and line width
    //static JTextField[][] boxes1=new JTextField [9][9];
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
                //if(boxes[i][j]==0){
                    SG.remove(boxes[i][j]);
                //}
            }
        }
        //SG.setMaximumSize(GridSize);
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
        //SG.setMaximumSize(GridSize);
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
        solvedpuzzle2 = new int[9][9];
        for(int row=0; row<3;row++){
            for(int col=0;col<3;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                //solvedpuzzle2[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
            }
        }
        available=numberHolder();
        for(int row=3; row<6;row++){
            for(int col=3;col<6;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                //solvedpuzzle2[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
                /*
                solvedpuzzle1[row][col]=available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle2[row][col]=solvedpuzzle1[row][col];
                available.remove((Object)solvedpuzzle1[row][col]);
                */
            }
        }
        available=numberHolder();
        for(int row=6; row<9;row++){
            for(int col=6;col<9;col++){
                int num = available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle1[row][col]=num;
                //solvedpuzzle2[row][col]=num;
                available.remove((Object)solvedpuzzle1[row][col]);
                /*
                solvedpuzzle1[row][col]=available.get((int)(Math.random() * ((available.size()))));
                solvedpuzzle2[row][col]=solvedpuzzle1[row][col];
                available.remove((Object)solvedpuzzle1[row][col]);
                */
            }
        }
        SudokuSolver.Solve(solvedpuzzle1);
        notsolvedpuzzle1=new int[9][9];
        notsolvedpuzzle2=new int[9][9];
        notsolvedpuzzle3=new int[9][9];
        notsolvedpuzzle4=new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                notsolvedpuzzle1[i][j]=solvedpuzzle1[i][j];
                //notsolvedpuzzle2[i][j]=solvedpuzzle1[i][j];
                //notsolvedpuzzle3[i][j]=solvedpuzzle1[i][j];
                //notsolvedpuzzle4[i][j]=solvedpuzzle1[i][j];
            }
        }
        
        //System.out.println(toStrings(notsolvedpuzzle1));
        //SudokuSolver.Solve(notsolvedpuzzle1);
        //System.out.println(toStrings(notsolvedpuzzle1));
        //SudokuSolver.Solve2(notsolvedpuzzle2);
        //System.out.println(toStrings(notsolvedpuzzle2));
        //SudokuSolver.Solve3(notsolvedpuzzle3);
        //System.out.println(toStrings(notsolvedpuzzle3));
        //SudokuSolver.Solve4(notsolvedpuzzle4);
        //System.out.println(toStrings(notsolvedpuzzle4));
        //create method to finalize sudoku
        while(stillSameSolution()){
            removeDiagonal();
        }
        /* do {
            removeDiagonal();
        }while(stillSameSolution());
         */
        LoadSudoku(notsolvedpuzzle1);
    }
    
    public static void removeDiagonal(){
        int x =(int)(Math.random() * 9);
        int y =(int)(Math.random() * 9);
        notsolvedpuzzle1[x][y]=0;
        notsolvedpuzzle1[y][x]=0;
        notsolvedpuzzle1[8-x][8-y]=0;
        notsolvedpuzzle1[8-y][8-x]=0;
    }

    public static boolean stillSameSolution(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                notsolvedpuzzle2[i][j]=notsolvedpuzzle1[i][j];
                notsolvedpuzzle3[i][j]=notsolvedpuzzle1[i][j];
                notsolvedpuzzle4[i][j]=notsolvedpuzzle1[i][j];
                notsolvedpuzzle5[i][j]=notsolvedpuzzle1[i][j];
            }
        }
        SudokuSolver.Solve(notsolvedpuzzle5);
        //System.out.println(toStrings(solvedpuzzle2));
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle5[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve2(notsolvedpuzzle2);
        //System.out.println(toStrings(solvedpuzzle2));
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle2[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve3(notsolvedpuzzle3);
        //System.out.println(toStrings(solvedpuzzle2));
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle3[i][j]){
                    return false;
                }
            }
        }
        SudokuSolver.Solve4(notsolvedpuzzle4);
        //System.out.println(toStrings(solvedpuzzle2));
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(solvedpuzzle1[i][j]!=notsolvedpuzzle4[i][j]){
                    return false;
                }
            }
        }
        
        return true;
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
        Help.setPreferredSize(new Dimension(BoxSize*5,BoxSize*2+20));
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

    static int counter=0;
    
    public static String toStrings(int[][] s1){
        
        String s="";
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(s1[i][j]==notsolvedpuzzle1[i][j]){
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
        //frame.setLayout(new BorderLayout());
        JMenuBar MB=new JMenuBar();
        JMenu M=new JMenu("Menu");
        JMenuItem MI1, MI2, MI3, MI4;//=new JMenuItem()
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
                LoadSudokuSolution(notsolvedpuzzle1, solvedpuzzle1);
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
        //frame.setPreferredSize(new Dimension(BoxSize*10,BoxSize*9+40));
        frame.setPreferredSize(new Dimension(700,700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}