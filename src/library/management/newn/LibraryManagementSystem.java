/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.management.newn;

/**
 *
 * @author georgina
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LibraryManagementSystem extends JFrame {
    
    private JPanel addLibrarianPanel;
    private JLabel toggleLabel;
    
    public LibraryManagementSystem() {
        initComponents();
    }
    
    private void initComponents() {
        // Create the toggle label with an initial "^" symbol
        toggleLabel = new JLabel("^");
        toggleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        toggleLabel.setForeground(Color.BLUE);
        toggleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        toggleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isVisible = addLibrarianPanel.isVisible();
                addLibrarianPanel.setVisible(!isVisible);
                updateToggleLabel();
            }
        });
        
        // Create the "add Librarian" panel
        addLibrarianPanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        addLibrarianPanel.add(usernameLabel);
        addLibrarianPanel.add(usernameTextField);
        addLibrarianPanel.add(passwordLabel);
        addLibrarianPanel.add(passwordField);
        addLibrarianPanel.setLayout(new GridLayout(2, 2));
        addLibrarianPanel.setPreferredSize(new Dimension(300, 60));
        addLibrarianPanel.setVisible(false);
        
        // Add the components to the JFrame
        setLayout(new BorderLayout());
        add(toggleLabel, BorderLayout.NORTH);
        add(addLibrarianPanel, BorderLayout.CENTER);
        
        // Add a component listener to the addLibrarianPanel to detect when it is shown
        addLibrarianPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateToggleLabel();
            }
        });
        
        setTitle("Library Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void updateToggleLabel() {
        // Update the toggle label's text and font to indicate whether the panel is visible or hidden
        if (addLibrarianPanel.isVisible()) {
            toggleLabel.setText("v");
            toggleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        } else {
            toggleLabel.setText("^");
            toggleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        }
    }
    
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.setVisible(true);
    }
}
