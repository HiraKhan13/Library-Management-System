import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

// Custom Exceptions
class EmptyFieldException extends Exception {

    public EmptyFieldException(String message) {
        super(message);
    }
}

class InvalidRollNumberException extends Exception {

    public InvalidRollNumberException(String message) {
        super(message);
    }
}

class InvalidDateException extends Exception {

    public InvalidDateException(String message) {
        super(message);
    }
}

class NullSelectionException extends Exception {

    public NullSelectionException(String message) {
        super(message);
    }
}

public class Frame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Labels
    JLabel lblTitle, lblName, lblRoll, lblBook, lblCategory;
    JLabel lblIssueDate, lblReturnDate, lblRemarks, lblType;

    // Text Fields
    JTextField txtName, txtRoll, txtBook, txtIssueDate, txtReturnDate;

    // Text Area
    JTextArea txtRemarks;

    // Combo Box
    JComboBox<String> comboCategory;

    // Radio Buttons
    JRadioButton rbNew, rbOld;
    ButtonGroup group;

    // Buttons
    JButton btnIssue, btnReset, btnExit;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    Frame frame = new Frame();
                    frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

    public Frame() {

        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 780, 720);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 240, 255));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(90, 30, 580, 610);
        panel.setLayout(null);

        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));

        contentPane.add(panel);

        // Title
        lblTitle = new JLabel("Library Management System");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(new Color(52, 73, 94));
        lblTitle.setBounds(100, 20, 400, 40);

        panel.add(lblTitle);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);

        // Student Name
        lblName = new JLabel("Student Name");
        lblName.setFont(labelFont);
        lblName.setBounds(40, 90, 150, 30);

        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(230, 90, 290, 35);

        panel.add(txtName);

        // Roll Number
        lblRoll = new JLabel("Roll Number");
        lblRoll.setFont(labelFont);
        lblRoll.setBounds(40, 140, 150, 30);

        panel.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(230, 140, 290, 35);

        panel.add(txtRoll);

        // Book Title
        lblBook = new JLabel("Book Title");
        lblBook.setFont(labelFont);
        lblBook.setBounds(40, 190, 150, 30);

        panel.add(lblBook);

        txtBook = new JTextField();
        txtBook.setBounds(230, 190, 290, 35);

        panel.add(txtBook);

        // Category
        lblCategory = new JLabel("Book Category");
        lblCategory.setFont(labelFont);
        lblCategory.setBounds(40, 240, 150, 30);

        panel.add(lblCategory);

        String categories[] = {
                "Select Category",
                "Programming",
                "AI",
                "Databases",
                "Networking"
        };

        comboCategory = new JComboBox<>(categories);
        comboCategory.setBounds(230, 240, 290, 35);

        panel.add(comboCategory);

        // Book Type
        lblType = new JLabel("Book Type");
        lblType.setFont(labelFont);
        lblType.setBounds(40, 290, 150, 30);

        panel.add(lblType);

        rbNew = new JRadioButton("New Edition");
        rbNew.setBounds(230, 290, 130, 30);
        rbNew.setBackground(Color.WHITE);

        panel.add(rbNew);

        rbOld = new JRadioButton("Old Edition");
        rbOld.setBounds(380, 290, 130, 30);
        rbOld.setBackground(Color.WHITE);

        panel.add(rbOld);

        group = new ButtonGroup();
        group.add(rbNew);
        group.add(rbOld);

        // Issue Date
        lblIssueDate = new JLabel("Issue Date");
        lblIssueDate.setFont(labelFont);
        lblIssueDate.setBounds(40, 340, 150, 30);

        panel.add(lblIssueDate);

        txtIssueDate = new JTextField();
        txtIssueDate.setBounds(230, 340, 290, 35);
        txtIssueDate.setToolTipText("Format: YYYY-MM-DD");

        panel.add(txtIssueDate);

        // Return Date
        lblReturnDate = new JLabel("Return Date");
        lblReturnDate.setFont(labelFont);
        lblReturnDate.setBounds(40, 390, 150, 30);

        panel.add(lblReturnDate);

        txtReturnDate = new JTextField();
        txtReturnDate.setBounds(230, 390, 290, 35);
        txtReturnDate.setToolTipText("Format: YYYY-MM-DD");

        panel.add(txtReturnDate);

        // Remarks
        lblRemarks = new JLabel("Remarks");
        lblRemarks.setFont(labelFont);
        lblRemarks.setBounds(40, 440, 150, 30);

        panel.add(lblRemarks);

        txtRemarks = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(txtRemarks);
        scrollPane.setBounds(230, 440, 290, 70);

        panel.add(scrollPane);

        // Issue Button
        btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(50, 540, 140, 40);
        btnIssue.setBackground(new Color(39, 174, 96));
        btnIssue.setForeground(Color.WHITE);
        btnIssue.setFocusPainted(false);

        btnIssue.addActionListener(this);

        panel.add(btnIssue);

        // Reset Button
        btnReset = new JButton("Reset");
        btnReset.setBounds(230, 540, 120, 40);
        btnReset.setBackground(new Color(241, 196, 15));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);

        btnReset.addActionListener(this);

        panel.add(btnReset);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(400, 540, 120, 40);
        btnExit.setBackground(new Color(231, 76, 60));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);

        btnExit.addActionListener(this);

        panel.add(btnExit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ISSUE BUTTON
        if (e.getSource() == btnIssue) {

            try {

                // Empty Field Exception
                if (txtName.getText().trim().isEmpty()
                        || txtRoll.getText().trim().isEmpty()
                        || txtBook.getText().trim().isEmpty()
                        || txtIssueDate.getText().trim().isEmpty()
                        || txtReturnDate.getText().trim().isEmpty()) {

                    throw new EmptyFieldException(
                            "Required fields cannot be empty!");
                }

                // Roll Number Validation
                String roll = txtRoll.getText();

                if (!roll.matches("\\d+")) {

                    throw new InvalidRollNumberException(
                            "Roll Number must contain only digits!");
                }

                // NumberFormatException
                int rollNo = Integer.parseInt(roll);

                // Category Validation
                if (comboCategory.getSelectedIndex() == 0) {

                    throw new NullSelectionException(
                            "Please select a book category!");
                }

                // Radio Button Validation
                if (!rbNew.isSelected() && !rbOld.isSelected()) {

                    throw new NullSelectionException(
                            "Please select a book type!");
                }

                // Date Validation
                LocalDate issueDate = LocalDate.parse(txtIssueDate.getText());
                LocalDate returnDate = LocalDate.parse(txtReturnDate.getText());

                if (returnDate.isBefore(issueDate)) {

                    throw new InvalidDateException(
                            "Return date cannot be earlier than issue date!");
                }

                String edition = "";

                if (rbNew.isSelected()) {
                    edition = "New Edition";
                }

                else {
                    edition = "Old Edition";
                }

                JOptionPane.showMessageDialog(this,

                        "Book Issued Successfully!\n\n"

                                + "Student Name: "
                                + txtName.getText()

                                + "\nRoll Number: "
                                + rollNo

                                + "\nBook Title: "
                                + txtBook.getText()

                                + "\nCategory: "
                                + comboCategory.getSelectedItem()

                                + "\nBook Type: "
                                + edition

                                + "\nIssue Date: "
                                + txtIssueDate.getText()

                                + "\nReturn Date: "
                                + txtReturnDate.getText()

                                + "\nRemarks: "
                                + txtRemarks.getText());

            }

            catch (EmptyFieldException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Empty Field Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (InvalidRollNumberException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Invalid Roll Number",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (InvalidDateException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Date Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (NullSelectionException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Selection Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Roll Number must be numeric!",
                        "Number Format Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (DateTimeParseException ex) {

                JOptionPane.showMessageDialog(this,
                        "Invalid Date Format! Use YYYY-MM-DD",
                        "Date Format Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            finally {

                JOptionPane.showMessageDialog(this,
                        "Operation Completed!");
            }
        }

        // RESET BUTTON
        if (e.getSource() == btnReset) {

            txtName.setText("");
            txtRoll.setText("");
            txtBook.setText("");
            txtIssueDate.setText("");
            txtReturnDate.setText("");
            txtRemarks.setText("");

            group.clearSelection();

            comboCategory.setSelectedIndex(0);
        }

        // EXIT BUTTON
        if (e.getSource() == btnExit) {

            System.exit(0);
        }
    }
}