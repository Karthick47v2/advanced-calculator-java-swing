//----Using Java Swing and AWT for GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.Math;

public class CalcFrame extends JFrame implements ActionListener{
    //-----Panel-----
    private JPanel opPanel, clrPanel;
    //-----TxttField-----
    private JTextField displayField;
    //-----Buttons-----
    private JButton[] numpadBtns = new JButton[10];
    private JButton[] opBtns = new JButton[17];
    private JButton mulBtn, divBtn, addBtn, subBtn, remBtn;
    private JButton sinBtn, cosBtn, tanBtn, invSinBtn, invCosBtn, cotBtn, boBtn, bcBtn;
    private JButton delBtn, clrBtn, equBtn, decBtn;

    private Font calcFont = new Font("Comic Sans", Font.PLAIN, 19);
    
    private boolean chkEval = false;

    CalcFrame(){
        super("Calculator");                              // window title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // close when ('x') pressed
        this.setSize(480, 640);                                     // window res
        this.setLayout(null);                                       // discard default layout

        displayField = new JTextField();
        displayField.setBounds(20, 30, 420, 50);                    // set pos
        displayField.setFont(calcFont);            
        displayField.setBackground(Color.DARK_GRAY); 
        displayField.setForeground(Color.LIGHT_GRAY); 
        displayField.setBorder(BorderFactory.createEtchedBorder()); 
        displayField.setEditable(false);                            // set text field as non editable by user -- in order to make it as a display

        // instantiate btns
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        remBtn = new JButton("%");
        delBtn = new JButton("C");
        clrBtn = new JButton("AC");
        equBtn = new JButton("=");
        decBtn = new JButton(".");
        sinBtn = new JButton("Sin");
        cosBtn = new JButton("Cos");
        tanBtn = new JButton("Tan");
        cotBtn = new JButton("Cot");   
        boBtn = new JButton("(");
        bcBtn = new JButton(")");     
        invSinBtn = new JButton("S. Inv");
        invCosBtn = new JButton("C. Inv");
        
        // add to array to add properties easily
        opBtns[0] = mulBtn;
        opBtns[1] = divBtn;
        opBtns[2] = addBtn;
        opBtns[3] = subBtn;
        opBtns[4] = remBtn;
        opBtns[5] = decBtn;
        opBtns[6] = delBtn;
        opBtns[7] = clrBtn;
        opBtns[8] = equBtn;
        opBtns[9] = boBtn;
        opBtns[10] = bcBtn;
        opBtns[11] = sinBtn;
        opBtns[12] = cosBtn;
        opBtns[13] = tanBtn;
        opBtns[14] = invSinBtn;
        opBtns[15] = invCosBtn;
        opBtns[16] = cotBtn;


        // add props to func bbtns
        for(int i = 0; i < 17; i++){
            opBtns[i].addActionListener(this);                          // listen to action
            opBtns[i].setFont(calcFont);
            opBtns[i].setBackground(Color.GREEN);
            opBtns[i].setFocusable(false);                              // to make btn look clean
            opBtns[i].setBorderPainted(false);                             // set no painted border
        }

        // add props to numpad
        for(int i = 0; i < 10; i++){
            numpadBtns[i] = new JButton(Integer.toString(i)); 
            numpadBtns[i].addActionListener(this);
            numpadBtns[i].setFont(calcFont);
            numpadBtns[i].setBackground(Color.LIGHT_GRAY);
            numpadBtns[i].setFocusable(false);
            numpadBtns[i].setBorderPainted(false); 
        }

        // instantiate panels and add btns inside it -- to make clean UI
        clrPanel = new JPanel();
        clrPanel.setBounds(10, 90, 440, 40);
        clrPanel.setLayout(new GridLayout(1, 2, 10, 10));
        clrPanel.setBackground(Color.BLACK);

        clrBtn.setBackground(Color.RED);
        delBtn.setBackground(Color.ORANGE);

        clrPanel.add(clrBtn);
        clrPanel.add(delBtn);

        opPanel = new JPanel();
        opPanel.setBounds(5, 140, 455, 430);
        opPanel.setLayout(new GridLayout(5, 5, 10, 10));
        opPanel.setBackground(Color.BLACK);

        opPanel.add(sinBtn);
        opPanel.add(cosBtn);
        opPanel.add(boBtn);
        opPanel.add(bcBtn);
        opPanel.add(remBtn);
        opPanel.add(tanBtn);
        opPanel.add(numpadBtns[7]);
        opPanel.add(numpadBtns[8]);
        opPanel.add(numpadBtns[9]);
        opPanel.add(cotBtn);
        opPanel.add(addBtn);
        opPanel.add(numpadBtns[4]);
        opPanel.add(numpadBtns[5]);
        opPanel.add(numpadBtns[6]);
        opPanel.add(subBtn);
        opPanel.add(mulBtn);
        opPanel.add(numpadBtns[1]);
        opPanel.add(numpadBtns[2]);
        opPanel.add(numpadBtns[3]);
        opPanel.add(divBtn);
        opPanel.add(decBtn);
        opPanel.add(invSinBtn);
        opPanel.add(numpadBtns[0]);
        opPanel.add(invCosBtn);
        opPanel.add(equBtn);
        
        // add panels and txtfield to frame aka window frame
        this.add(displayField);
        this.add(clrPanel);
        this.add(opPanel);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);                                           // disable ability to resize
        this.setVisible(true);                                              // make frame visible to user
    }

    // func to perform action when repestive btn is pressed
    @Override
    public void actionPerformed(ActionEvent e){
        // display nums
        for(int i = 0; i < 10; i++){
            if(e.getSource() == numpadBtns[i]){
                if(!chkEval) displayField.setText(displayField.getText().concat(Integer.toString(i)));
                else{
                    chkEval = false;
                    displayField.setText(Integer.toString(i));
                }
            }
        }

        // display ops
        char[] opChars = {'*', '/', '+', '-', '%', '.'};
        String[] trigStrings = {"(", ")","sin", "cos", "tan", "invsin", "invcos", "cot"};

        for(int i = 0; i < 6; i++){
            chkEval = false;
            if(e.getSource() == opBtns[i]) displayField.setText(displayField.getText() + opChars[i]);
        }

        for(int i = 9; i < 17; i++){
            if(e.getSource() == opBtns[i]){
                if(!chkEval) displayField.setText(displayField.getText() + trigStrings[i - 9]); 
                else{
                    chkEval = false;
                    displayField.setText(trigStrings[i - 9]);
                }
            }
        }

        if(e.getSource() == clrBtn) displayField.setText("");

        else if(e.getSource() == equBtn){
            double ans = evalAns(displayField.getText());
            displayField.setText(Double.toString(ans));
            chkEval = true;
        }

        // in order to dlt last char loop thr n-1 chars of string
        else if(e.getSource() == delBtn){
            String str = displayField.getText();
            displayField.setText("");

            int n = 1;                                                                                                  // normal case
            if(str.length() >= 6 && (str.charAt(str.length() - 6) == 'i') && (str.charAt(str.length() - 4) == 'v')) n = 6;                                       // inv
            else if(str.length() >= 3 && (str.charAt(str.length() - 3) == 's' || str.charAt(str.length() - 3) == 'c'    // sin cost tan cot NaN
                 || str.charAt(str.length() - 3) == 't' || str.charAt(str.length() - 3) == 'N')) n = 3;

            for(int i = 0; i < str.length() - n; i++){
                displayField.setText(displayField.getText() + str.charAt(i));
            }
        }
    }

    //func to calc ans using expression string
    private double evalAns(String str){
        return new Object() {
            int pos = -1;
            int ch = 0;

            // func to parse string expression into actual one
            private double parse(){
                nextChar();
                double ans = 0;

                // if = pressed when blank
                if(str.length() == 0){
                    JOptionPane.showMessageDialog(null, "Enter an expression", "No Expression", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    // errors like 0.00.1 -- two decimal points
                    try{
                        ans = parseExpression();
                    }
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "NumberFormatException", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return ans;
            }
            
            // increment char pointer pos
            private void nextChar(){
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            // chk char pos
            private boolean exp(int ex){
                if(ch == ex){
                    nextChar();
                    return true;
                }
                return false;
            }

            // reduce expr into add and subs
            private double parseExpression(){
                double ans = parseTerm();
                while(true){
                    if(exp('+')) ans += parseTerm();                    // add
                    else if(exp('-')) ans -= parseTerm();               // sub
                    else return ans;
                }
            }

            // reduce further into mul, rem and div
            private double parseTerm(){
                double ans = parseFactor();
                if(ans == 0) return 0;

                while(true){
                    if(exp('*')) ans *= parseFactor();                  // muln
                    else if(exp('%')){ 
                        double p = parseFactor();                       // rem
                        if(p == 0){
                            JOptionPane.showMessageDialog(null, "Divisor can't be zero", "Error", JOptionPane.ERROR_MESSAGE);
                            return 0;
                        }
                        ans %= p;
                    }
                    else if(exp('/')) {
                        double p = parseFactor(); 
                        if(p == 0){
                            JOptionPane.showMessageDialog(null, "Division by Zero", "Error", JOptionPane.ERROR_MESSAGE);
                            return 0;
                        }
                        ans /= p;                                       // div
                    }
                    else return ans;
                }
            }

            // detailed parsing
            private double parseFactor(){
                // switch sign for whole nums
                if(exp('+')) return parseFactor();
                if(exp('-')) return -parseFactor();

                double ans = 0;
                int startPos = this.pos;

                // if parentheese encountered
                if(exp('(')){ 
                    ans = parseExpression();
                    if(!exp(')')){
                        JOptionPane.showMessageDialog(null, "Parenthesis not closed", "Error", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                }

                else if(str.charAt(pos) == ')'){
                    JOptionPane.showMessageDialog(null, "Parenthesis error", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }

                // if decimal no encountered
                else if((ch >= '0' && ch <= '9') || ch == '.'){
                    while((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    ans = Double.parseDouble(str.substring(startPos, this.pos));
                    if(ch == 40) ans *= parseFactor();                              // case like 7(2)
                    else if(ch == 41) parseFactor();
                } 

                // if trig funcs encountered
                else if(ch >= 'a' && ch <= 'z'){
                    while(ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    ans = parseFactor();
                    if(func.equals("sin")) ans = Math.sin(Math.toRadians(ans));
                    else if(func.equals("cos")) ans = Math.cos(Math.toRadians(ans));
                    else if(func.equals("tan")) ans = Math.tan(Math.toRadians(ans));
                    else if(func.equals("cot")) ans = 1.0 / Math.tan(Math.toRadians(ans));
                    else if(func.equals("invsin")) ans = Math.toDegrees(Math.asin(ans));
                    else if(func.equals("invcos")) ans = Math.toDegrees(Math.acos(ans));
                    // cases like tansin
                    else{
                        JOptionPane.showMessageDialog(null, "Unknown function", "Error", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                } 
                // cases like tan * sin -- w/o args
                else{
                    JOptionPane.showMessageDialog(null, "Not an Expression", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                // for ex .. sin-1(5)
                if(Double.toString(ans) == "NaN"){
                    JOptionPane.showMessageDialog(null, "Math ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return ans;
            }
        }.parse();
    }
}
