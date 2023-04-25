package library.management.newn;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.SQLException;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class LibrarianDashboard extends javax.swing.JFrame {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    Color mouseExitColor = new Color(144, 0, 0);
    Color mouseClickColor = new Color(96, 0, 0);
    DefaultTableModel model;

    static String BOOKTITLE, MEMBERID;

    /*private Color hoverBgColor = new Color(255, 255, 255); 
    private Color hoverTextColor = new Color(144, 0, 63); 
    private Color pressedBgColor = new Color(199, 0, 57); 
    private Color releasedBgColor = new Color(144, 0, 63); */
    public LibrarianDashboard() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        showBookDataToTable();
        showMemberDataToTable();
        showIssuedBooksDataToTable();
        displayTotalOfBooks();
        displaynoOfBorrowers();
        displaytotalofReturnedBooks();
        displaynoOfAvailableBooks();
        displaynoOfUnAvailableBooks();
        displaytotalofnoIssuedBooks();
        showReturnedBooksDataToTable();
    
       

    }

    // This method fetches book data from the database table and displays it in a JTable
    public void showBookDataToTable() {

        // Declare and initialize variables to store book data
        String BookId = null;
        String BookTitle = null;
        String Author = null;
        String Publisher = null;
        String Category = null;
        String Status = null;

        //to select all data from the book_details table
        String selectData = "SELECT * FROM book_details";

        // Creating a table model with rows that will be displayed on the CRUD table of librarians information
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfBooks.getModel();
        // Clear the table of any existing data
        model.setRowCount(0);

        try {
            // Load the MySQL JDBC driver and establish a connection to the databas
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");;
            // Create a prepared statement from the SQL statement to select data from the book_details table
            pst = con.prepareStatement(selectData);
            // Execute the SQL statement and get the result set
            rs = pst.executeQuery();

            // Loop through the result set and retrieve book data
            while (rs.next()) {

                BookId = rs.getString("book_id");
                BookTitle = rs.getString("book_title");
                Author = rs.getString("author");
                Publisher = rs.getString("publisher");
                Category = rs.getString("category");
                int Quantity = rs.getInt("quantity");
                Status = rs.getString("status");

                // Create a string array with the retrieved book data
                Object[] row = {BookId, BookTitle, Author, Publisher, Category, Quantity, Status};
                // Add the book data to the JTable model
                model.addRow(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showMemberDataToTable() {

        String Id = null;
        String FirstName = null;
        String MiddleName = null;
        String LastName = null;
        String Suffix = null;
        String Section = null;
        String SchoolYear = null;
        String Contact = null;
        String Gender = null;
        String MemberType = null;
        String GradeLevel = null;
        String Strand = null;

        //to select all data from the book_details table
        String selectData = "SELECT * FROM memberinfo_table";

        // Creating a table model with rows that will be displayed on the CRUD table of Members information
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfmembers.getModel();
        // Clear the table of any existing data
        model.setRowCount(0);

        try {
            // Load the MySQL JDBC driver and establish a connection to the databas
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            // Create a prepared statement from the SQL statement to select data from the book_details table
            pst = con.prepareStatement(selectData);
            // Execute the SQL statement and get the result set
            rs = pst.executeQuery();

            // Loop through the result set and retrieve book data
            while (rs.next()) {

                Id = rs.getString("member_id");
                FirstName = rs.getString("first_name");
                MiddleName = rs.getString("middle_name");
                LastName = rs.getString("last_name");
                Suffix = rs.getString("suffix");
                Section = rs.getString("section");
                SchoolYear = rs.getString("school_year");
                Contact = rs.getString("contact");
                Gender = rs.getString("gender");
                MemberType = rs.getString("member_type");
                GradeLevel = rs.getString("grade_level");
                Strand = rs.getString("strand");

                // Create a string array with the retrieved book data
                String rows[] = {Id, FirstName, MiddleName, LastName, Suffix, Section, SchoolYear, Contact, Gender, MemberType, GradeLevel, Strand};
                // Add the book data to the JTable model
                model.addRow(rows);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showIssuedBooksDataToTable() {
        try {
            String sql = "SELECT book_title, category, member_id, member_name, member_type, date_borrowed, due_date, penalty, books_borrowed, status FROM issued_books GROUP BY book_title, member_id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Remove existing rows from the table
            DefaultTableModel model = (DefaultTableModel) tableDataOfListOfIssuedBooks.getModel();
            model.setRowCount(0);

            // Add new rows to the table
            while (rs.next()) {

                String bookTitle = rs.getString("book_title");
                String category = rs.getString("category");
                String memberId = rs.getString("member_id");
                String memberName = rs.getString("member_name");
                String memberType = rs.getString("member_type");
                String dateBorrowed = rs.getString("date_borrowed");
                String dueDate = rs.getString("due_date");
                String penalty = rs.getString("penalty");
                int booksBorrowed = rs.getInt("books_borrowed");
                String status = rs.getString("status");

                Object[] row = {bookTitle, category, memberId, memberName, memberType, dateBorrowed, dueDate, penalty, booksBorrowed, status};
                model.addRow(row);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void showReturnedBooksDataToTable() {
        try {
            String sql = "SELECT book_title, member_id,Member_name, quantity_returned, returned_date, status FROM book_returned";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Remove existing rows from the table
            DefaultTableModel model = (DefaultTableModel) crud_tableDataOfReturnedBooks.getModel();
            model.setRowCount(0);

            // Add new rows to the table
            while (rs.next()) {
                String bookTitle = rs.getString("book_title");
                String memberID = rs.getString("member_id");
                String memberNAME = rs.getString("Member_name");
                int quantityReturned = rs.getInt("quantity_returned");
                String returnedDate = rs.getString("returned_date");
                String status = rs.getString("status");

                Object[] row = {bookTitle, memberID, memberNAME, quantityReturned, returnedDate, status};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double CalculatePenalty(LocalDateTime dueDate, LocalDateTime currentDate) {
        double penaltyAmount = Double.parseDouble(txt_Penalty.getText());
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, currentDate);

        // Check if the book is overdue
        if (daysOverdue > 1) {
            // Calculate penalty based on the number of days overdue
            // Penalty rate of 15 pesos per day overdue
            penaltyAmount = daysOverdue * Double.parseDouble(txt_Penalty.getText());
        }
        // Check if the current date is after the due date
        if (currentDate.isAfter(dueDate)) {
            // Add an additional penalty of 50 pesos if the book is still not returned after the due date
            penaltyAmount += Double.parseDouble(txt_Penalty.getText());
        }

        return penaltyAmount;
    }

    public void displayTotalOfBooks() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT COUNT(*) AS book_id FROM book_details";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int totalBooks = rs.getInt(1);
                txttotalbooks.setText(String.valueOf(totalBooks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displaynoOfBorrowers() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT COUNT(*) AS issue_id FROM issued_books";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int noOfBorr = rs.getInt(1);
                txtNoofBorrowers.setText(String.valueOf(noOfBorr));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displaytotalofReturnedBooks() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT COUNT(*) AS id FROM book_returned";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int totalofreturnedbooks = rs.getInt(1);
                txtTotalreturnedbooks.setText(String.valueOf(totalofreturnedbooks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displaynoOfAvailableBooks() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT COUNT(*) AS status FROM book_details WHERE status = 'available'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int noOfAvailableBooks = rs.getInt("status"); // Update the index to the column alias "status"
                txtnoofavailablebooks.setText(String.valueOf(noOfAvailableBooks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displaynoOfUnAvailableBooks() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT COUNT(*) AS status FROM book_details WHERE status = 'Not Available'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int noOfunAvailableBooks = rs.getInt("status"); // Update the index to the column alias "status"
                txtnoofunavailablebooks.setText(String.valueOf(noOfunAvailableBooks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displaytotalofnoIssuedBooks() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT SUM(books_borrowed) AS books_borrowed FROM issued_books";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int totalofIssuedBOoks = rs.getInt("books_borrowed");
                txtnoOfissuedBooks.setText(String.valueOf(totalofIssuedBOoks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     public void DisplaynoTotalofPenalties() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/computerizedlibrarysys", "root", "");
            String sql = "SELECT SUM(books_borrowed) AS books_borrowed FROM issued_books";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            if (rs.next()) {
                int totalofIssuedBOoks = rs.getInt("books_borrowed");
                txtnoOfPenalties.setText(String.valueOf(totalofIssuedBOoks));
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public LibrarianDashboard(String username) {
        initComponents();
        LibrarianNameDisplay.setText(username);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSMetroTextPlaceHolderBeanInfo1 = new rojerusan.RSMetroTextPlaceHolderBeanInfo();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LibrarianNameDisplay = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Parent = new javax.swing.JPanel();
        dashboardCardLayout = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel23 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtnoofborrowers = new javax.swing.JPanel();
        txtNoofBorrowers = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txttotalbooks = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtnoOfPenalties = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtnoOfissuedBooks = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        txtTotalreturnedbooks = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        txtnoofunavailablebooks = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtnoofavailablebooks = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        crud_tableDataOfReturnedBooks = new rojerusan.RSTableMetro();
        jPanel26 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        crud_deleteBtn1 = new rojerusan.RSMaterialButtonRectangle();
        listOfIssuedBooksCardLayout = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDataOfListOfIssuedBooks = new rojerusan.RSTableMetro();
        jPanel24 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        buttonforReturnBookInListOfIssuedBooks = new rojerusan.RSMaterialButtonRectangle();
        txtSearchMemberIdMemberNameBookTitle = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        bookTransactionCardLayout = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        BookQuantitylabelForBookDetails = new javax.swing.JLabel();
        BookAuthorLabelForBookDetails = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_BookTitle_BookTransac = new javax.swing.JTextField();
        SearchButtonForBookId = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        BookCategoryLabelForBookDetails = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        MemberSectionLabelForStudentDetails = new javax.swing.JLabel();
        MemberNameLabelForStudentDetails = new javax.swing.JLabel();
        MemberTypeLabelForStudentDetails = new javax.swing.JLabel();
        MemberStrandLabelForStudentDetails = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        SearchButtonForMemberId = new javax.swing.JButton();
        txt_MemberId_BookTransac = new javax.swing.JTextField();
        buttonforIssueBookOfBookTransaction = new rojerusan.RSMaterialButtonRectangle();
        txt_Penalty = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txt_BooksToBeBorrowed = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txt_MaxDaysAllowed = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        memberMaintenanceCardLayout = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        crud_tableDataOfmembers = new rojerusan.RSTableMetro();
        jPanel19 = new javax.swing.JPanel();
        firstname_label = new javax.swing.JLabel();
        txt_Memberfirstname = new javax.swing.JTextField();
        middlename_label = new javax.swing.JLabel();
        txt_Membermiddlename = new javax.swing.JTextField();
        lastname_label = new javax.swing.JLabel();
        txtsuffix = new javax.swing.JTextField();
        jComboBox_Membergender = new javax.swing.JComboBox<>();
        membertype_label = new javax.swing.JLabel();
        jComboBox_strand = new javax.swing.JComboBox<>();
        gradelevel_label = new javax.swing.JLabel();
        sectionstrand_label = new javax.swing.JLabel();
        contact_label = new javax.swing.JLabel();
        txt_Membercontact = new javax.swing.JTextField();
        schoolyear_label = new javax.swing.JLabel();
        txt_Membersection = new javax.swing.JTextField();
        schoolyear_label1 = new javax.swing.JLabel();
        jComboBox_gradelevel = new javax.swing.JComboBox<>();
        jComboBox_membertype = new javax.swing.JComboBox<>();
        gender_label2 = new javax.swing.JLabel();
        formattedtxt_schoolyear = new javax.swing.JFormattedTextField();
        crud_MemberAdd = new rojerusan.RSMaterialButtonRectangle();
        crud_MemberupdateBtn = new rojerusan.RSMaterialButtonRectangle();
        crud_MemberdeleteBtn = new rojerusan.RSMaterialButtonRectangle();
        crud_MemberclearBtn = new rojerusan.RSMaterialButtonRectangle();
        txt_Memberlastname = new javax.swing.JTextField();
        lastname_label1 = new javax.swing.JLabel();
        labelsearchmemberid = new javax.swing.JLabel();
        txtSearchmemberidForMemberMaint = new javax.swing.JTextField();
        bookMaintenanceCardLayout = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel20 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txt_author = new javax.swing.JTextField();
        txt_booktitle = new javax.swing.JTextField();
        txt_category = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox_statusBook = new javax.swing.JComboBox<>();
        crud_addBtn = new rojerusan.RSMaterialButtonRectangle();
        crud_deleteBtn = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        crud_tableDataOfBooks = new rojerusan.RSTableMetro();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        txt_publisher = new javax.swing.JTextField();
        crud_clearBtn = new rojerusan.RSMaterialButtonRectangle();
        labelsearchbooktitle = new javax.swing.JLabel();
        txtSearchBookTitle = new javax.swing.JTextField();
        crud_updateBtn2 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(144, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(144, 0, 0));

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-books-24.png"))); // NOI18N
        jLabel17.setText("List of Issued Books/Return");
        jLabel17.setToolTipText("");
        jLabel17.setFocusable(false);
        jLabel17.setIconTextGap(0);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel17MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 250, 40));

        jPanel13.setBackground(new java.awt.Color(144, 0, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-transaction-24.png"))); // NOI18N
        jLabel1.setText(" Issue Book");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 40));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 250, 40));

        jPanel16.setBackground(new java.awt.Color(144, 0, 0));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-dashboard-24.png"))); // NOI18N
        jLabel2.setText(" Dashboard");
        jLabel2.setToolTipText("");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 250, 50));

        jButton1.setBackground(new java.awt.Color(243, 225, 192));
        jButton1.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsFor-LibrarianModule/icons8-lougut24.png"))); // NOI18N
        jButton1.setText("LOG OUT");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setVerifyInputWhenFocusTarget(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 100, 30));

        jPanel5.setBackground(new java.awt.Color(144, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 240, -1));

        jPanel6.setBackground(new java.awt.Color(144, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-add-book-24.png"))); // NOI18N
        jLabel14.setText(" Book Maintenance ");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel14MouseReleased(evt);
            }
        });
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 250, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 250, 40));

        jSeparator1.setForeground(new java.awt.Color(241, 184, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 140, 10));

        jPanel7.setBackground(new java.awt.Color(144, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-user-groups-24.png"))); // NOI18N
        jLabel10.setText(" Member Maintenance ");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel10MouseReleased(evt);
            }
        });
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 40));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 250, 40));

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-librarian-64.png"))); // NOI18N
        jLabel20.setText("LIBRARIAN");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, -1));

        jPanel2.setBackground(new java.awt.Color(144, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(494, 33));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Welcome :");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 230, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setText("Dashboard");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 0, 1120, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 770));

        jPanel3.setBackground(new java.awt.Color(144, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(494, 33));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LibrarianNameDisplay.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        LibrarianNameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(LibrarianNameDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, 90, 20));

        jLabel22.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Welcome :");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 90, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 0, 1120, 50));

        Parent.setBackground(new java.awt.Color(255, 255, 255));
        Parent.setLayout(new java.awt.CardLayout());

        dashboardCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        dashboardCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setText("Total Books");
        jPanel23.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 260, 30));

        txtnoofborrowers.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        txtnoofborrowers.setPreferredSize(new java.awt.Dimension(260, 1));

        txtNoofBorrowers.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtNoofBorrowers.setForeground(new java.awt.Color(102, 102, 102));
        txtNoofBorrowers.setText("5");

        jLabel53.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 102, 102));
        jLabel53.setText("5");

        javax.swing.GroupLayout txtnoofborrowersLayout = new javax.swing.GroupLayout(txtnoofborrowers);
        txtnoofborrowers.setLayout(txtnoofborrowersLayout);
        txtnoofborrowersLayout.setHorizontalGroup(
            txtnoofborrowersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtnoofborrowersLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(txtnoofborrowersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtnoofborrowersLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtnoofborrowersLayout.createSequentialGroup()
                        .addComponent(txtNoofBorrowers, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        txtnoofborrowersLayout.setVerticalGroup(
            txtnoofborrowersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtnoofborrowersLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtNoofBorrowers, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(txtnoofborrowers, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 260, 140));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Total of Returned Books");
        jPanel23.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 260, 30));

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel11.setPreferredSize(new java.awt.Dimension(260, 1));

        txttotalbooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txttotalbooks.setForeground(new java.awt.Color(102, 102, 102));
        txttotalbooks.setText("10");

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("5");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(txttotalbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txttotalbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 260, 140));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel24.setText("No. of Borrowers");
        jPanel23.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 260, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel25.setText("No. of Total penalties");
        jPanel23.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 260, 30));

        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel14.setPreferredSize(new java.awt.Dimension(260, 1));

        txtnoOfPenalties.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtnoOfPenalties.setForeground(new java.awt.Color(102, 102, 102));
        txtnoOfPenalties.setText("5");

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("5");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(txtnoOfPenalties, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtnoOfPenalties, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, -1, 140));

        jPanel27.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel27.setPreferredSize(new java.awt.Dimension(260, 1));

        txtnoOfissuedBooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtnoOfissuedBooks.setForeground(new java.awt.Color(102, 102, 102));
        txtnoOfissuedBooks.setText("5");

        jLabel55.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setText("5");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addComponent(txtnoOfissuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtnoOfissuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, -1, 140));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel30.setText("No. of Issued Books");
        jPanel23.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 270, 220, 30));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel17.setPreferredSize(new java.awt.Dimension(260, 1));

        txtTotalreturnedbooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtTotalreturnedbooks.setForeground(new java.awt.Color(102, 102, 102));
        txtTotalreturnedbooks.setText("5");

        jLabel32.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("5");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(txtTotalreturnedbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtTotalreturnedbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, -1, 140));

        jPanel28.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel28.setPreferredSize(new java.awt.Dimension(260, 1));

        txtnoofunavailablebooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtnoofunavailablebooks.setForeground(new java.awt.Color(102, 102, 102));
        txtnoofunavailablebooks.setText("5");

        jLabel61.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 102, 102));
        jLabel61.setText("5");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(txtnoofunavailablebooks, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtnoofunavailablebooks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, -1, 140));

        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel31.setText("No. of Unavailable Books");
        jPanel23.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 260, 30));

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel15.setPreferredSize(new java.awt.Dimension(260, 1));

        txtnoofavailablebooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        txtnoofavailablebooks.setForeground(new java.awt.Color(102, 102, 102));
        txtnoofavailablebooks.setText("5");

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("5");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(txtnoofavailablebooks, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtnoofavailablebooks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, 140));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel26.setText("No. of Available Books");
        jPanel23.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 260, 30));

        crud_tableDataOfReturnedBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book Title", "Member Id ", "Member Name", "Quantity   ", " Returned Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        crud_tableDataOfReturnedBooks.setAltoHead(30);
        crud_tableDataOfReturnedBooks.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfReturnedBooks.setColorBordeFilas(new java.awt.Color(204, 204, 204));
        crud_tableDataOfReturnedBooks.setColorBordeHead(new java.awt.Color(255, 255, 255));
        crud_tableDataOfReturnedBooks.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        crud_tableDataOfReturnedBooks.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        crud_tableDataOfReturnedBooks.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        crud_tableDataOfReturnedBooks.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        crud_tableDataOfReturnedBooks.setColorSelBackgound(new java.awt.Color(79, 126, 171));
        crud_tableDataOfReturnedBooks.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        crud_tableDataOfReturnedBooks.setFuenteHead(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        crud_tableDataOfReturnedBooks.setGrosorBordeFilas(0);
        crud_tableDataOfReturnedBooks.setGrosorBordeHead(0);
        crud_tableDataOfReturnedBooks.setMinimumSize(new java.awt.Dimension(100, 580));
        crud_tableDataOfReturnedBooks.setRowHeight(18);
        crud_tableDataOfReturnedBooks.setSelectionBackground(new java.awt.Color(102, 102, 255));
        crud_tableDataOfReturnedBooks.setShowGrid(true);
        crud_tableDataOfReturnedBooks.getTableHeader().setReorderingAllowed(false);
        crud_tableDataOfReturnedBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfReturnedBooksMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(crud_tableDataOfReturnedBooks);
        if (crud_tableDataOfReturnedBooks.getColumnModel().getColumnCount() > 0) {
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(0).setPreferredWidth(200);
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(1).setPreferredWidth(15);
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(2).setPreferredWidth(200);
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(3).setPreferredWidth(15);
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(4).setPreferredWidth(15);
            crud_tableDataOfReturnedBooks.getColumnModel().getColumn(5).setPreferredWidth(17);
        }

        jPanel23.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 1060, 260));

        jPanel26.setBackground(new java.awt.Color(204, 204, 204));

        jLabel36.setBackground(new java.awt.Color(204, 204, 204));
        jLabel36.setFont(new java.awt.Font("Charlemagne Std", 1, 14)); // NOI18N
        jLabel36.setText("        ");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 350, 30));

        jPanel29.setBackground(new java.awt.Color(204, 204, 204));

        jLabel50.setBackground(new java.awt.Color(204, 204, 204));
        jLabel50.setFont(new java.awt.Font("Charlemagne Std", 1, 14)); // NOI18N
        jLabel50.setText("List of returned books");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 77, Short.MAX_VALUE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel23.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 670, 350, 30));
        jPanel23.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 660, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel23.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 663, 1110, 10));

        crud_deleteBtn1.setBackground(new java.awt.Color(39, 80, 112));
        crud_deleteBtn1.setText("DELETE");
        crud_deleteBtn1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_deleteBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_deleteBtn1ActionPerformed(evt);
            }
        });
        jPanel23.add(crud_deleteBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 970, 130, 40));

        jScrollPane9.setViewportView(jPanel23);

        dashboardCardLayout.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 720));
        jScrollPane9.getVerticalScrollBar().setUnitIncrement(20);

        Parent.add(dashboardCardLayout, "card2");

        listOfIssuedBooksCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        listOfIssuedBooksCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableDataOfListOfIssuedBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Book Title", "Category", "Member Id", "Member Name", "Member Type", "Date Barrowed", "Due Date", "Penalty", "Books Borrowed", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataOfListOfIssuedBooks.setAltoHead(30);
        tableDataOfListOfIssuedBooks.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        tableDataOfListOfIssuedBooks.setColorBordeFilas(new java.awt.Color(204, 204, 204));
        tableDataOfListOfIssuedBooks.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tableDataOfListOfIssuedBooks.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tableDataOfListOfIssuedBooks.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tableDataOfListOfIssuedBooks.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tableDataOfListOfIssuedBooks.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tableDataOfListOfIssuedBooks.setColorSelBackgound(new java.awt.Color(79, 126, 171));
        tableDataOfListOfIssuedBooks.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableDataOfListOfIssuedBooks.setFuenteHead(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableDataOfListOfIssuedBooks.setGrosorBordeFilas(0);
        tableDataOfListOfIssuedBooks.setGrosorBordeHead(0);
        tableDataOfListOfIssuedBooks.setShowGrid(true);
        tableDataOfListOfIssuedBooks.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tableDataOfListOfIssuedBooks);
        if (tableDataOfListOfIssuedBooks.getColumnModel().getColumnCount() > 0) {
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(0).setPreferredWidth(99);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(1).setPreferredWidth(40);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(2).setPreferredWidth(22);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(4).setPreferredWidth(33);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(5).setPreferredWidth(43);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(6).setPreferredWidth(40);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(7).setPreferredWidth(20);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(8).setPreferredWidth(50);
            tableDataOfListOfIssuedBooks.getColumnModel().getColumn(9).setPreferredWidth(17);
        }

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 1100, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setBackground(new java.awt.Color(241, 184, 20));
        jLabel59.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel59.setText("Search Member ID/Member Name and Book Title : ");
        jPanel24.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 31));

        buttonforReturnBookInListOfIssuedBooks.setBackground(new java.awt.Color(39, 80, 112));
        buttonforReturnBookInListOfIssuedBooks.setText("Return Book");
        buttonforReturnBookInListOfIssuedBooks.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonforReturnBookInListOfIssuedBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonforReturnBookInListOfIssuedBooksActionPerformed(evt);
            }
        });
        jPanel24.add(buttonforReturnBookInListOfIssuedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 150, 50));

        txtSearchMemberIdMemberNameBookTitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtSearchMemberIdMemberNameBookTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSearchMemberIdMemberNameBookTitle.setSelectionStart(2);
        txtSearchMemberIdMemberNameBookTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMemberIdMemberNameBookTitleActionPerformed(evt);
            }
        });
        txtSearchMemberIdMemberNameBookTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMemberIdMemberNameBookTitleKeyReleased(evt);
            }
        });
        jPanel24.add(txtSearchMemberIdMemberNameBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 10, 350, 30));

        jPanel4.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1102, 50));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel47.setText("List of Issued Books & Return Book/s");
        jPanel25.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 340, 40));

        jPanel4.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 380, 40));

        jScrollPane8.setViewportView(jPanel4);

        listOfIssuedBooksCardLayout.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 720));

        Parent.add(listOfIssuedBooksCardLayout, "card3");

        bookTransactionCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "ISSUE BOOK", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 24))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setBackground(new java.awt.Color(241, 184, 20));
        jLabel44.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel44.setText("Penalty:");
        jPanel10.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 70, 31));

        jPanel22.setBackground(new java.awt.Color(128, 0, 0));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(128, 0, 0)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(241, 184, 20));
        jLabel34.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Book Author");
        jPanel22.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 31));

        jLabel33.setBackground(new java.awt.Color(241, 184, 20));
        jLabel33.setFont(new java.awt.Font("Yu Gothic Medium", 0, 17)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Book Title");
        jPanel22.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, 40));

        jLabel35.setBackground(new java.awt.Color(241, 184, 20));
        jLabel35.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Quantity");
        jPanel22.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 31));

        BookQuantitylabelForBookDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BookQuantitylabelForBookDetails.setText("---");
        jPanel22.add(BookQuantitylabelForBookDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 237, 390, -1));

        BookAuthorLabelForBookDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BookAuthorLabelForBookDetails.setText("---");
        jPanel22.add(BookAuthorLabelForBookDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 147, 390, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("BOOK DETAILS");
        jPanel22.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 230, 30));

        txt_BookTitle_BookTransac.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txt_BookTitle_BookTransac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_BookTitle_BookTransac.setSelectionStart(2);
        txt_BookTitle_BookTransac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BookTitle_BookTransacActionPerformed(evt);
            }
        });
        jPanel22.add(txt_BookTitle_BookTransac, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 260, 30));

        SearchButtonForBookId.setBackground(new java.awt.Color(255, 204, 102));
        SearchButtonForBookId.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        SearchButtonForBookId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsFor-LibrarianModule/icons8-search-24.png"))); // NOI18N
        SearchButtonForBookId.setText("Search");
        SearchButtonForBookId.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SearchButtonForBookId.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        SearchButtonForBookId.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        SearchButtonForBookId.setIconTextGap(2);
        SearchButtonForBookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonForBookIdActionPerformed(evt);
            }
        });
        jPanel22.add(SearchButtonForBookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 90, -1));

        jLabel46.setBackground(new java.awt.Color(241, 184, 20));
        jLabel46.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Category");
        jPanel22.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 31));

        BookCategoryLabelForBookDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BookCategoryLabelForBookDetails.setText("---");
        jPanel22.add(BookCategoryLabelForBookDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 187, 390, -1));

        jPanel10.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 520, 330));

        jPanel21.setBackground(new java.awt.Color(153, 153, 153));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setBackground(new java.awt.Color(241, 184, 20));
        jLabel40.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jPanel21.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 31));

        jLabel38.setBackground(new java.awt.Color(241, 184, 20));
        jLabel38.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Section");
        jPanel21.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 60, 31));

        jLabel39.setBackground(new java.awt.Color(241, 184, 20));
        jLabel39.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Member Name");
        jPanel21.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 31));

        jLabel41.setBackground(new java.awt.Color(241, 184, 20));
        jLabel41.setFont(new java.awt.Font("Yu Gothic Medium", 0, 17)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Member ID");
        jPanel21.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 91, 100, 40));

        jLabel42.setBackground(new java.awt.Color(241, 184, 20));
        jLabel42.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Member Type");
        jPanel21.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 31));

        jLabel43.setBackground(new java.awt.Color(241, 184, 20));
        jLabel43.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Strand");
        jPanel21.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, 31));

        MemberSectionLabelForStudentDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MemberSectionLabelForStudentDetails.setText("---");
        jPanel21.add(MemberSectionLabelForStudentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 350, -1));

        MemberNameLabelForStudentDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MemberNameLabelForStudentDetails.setText("---");
        jPanel21.add(MemberNameLabelForStudentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 125, 350, 30));

        MemberTypeLabelForStudentDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MemberTypeLabelForStudentDetails.setText("---");
        jPanel21.add(MemberTypeLabelForStudentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 350, -1));

        MemberStrandLabelForStudentDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MemberStrandLabelForStudentDetails.setText("---");
        jPanel21.add(MemberStrandLabelForStudentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 350, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("STUDENT DETAILS");
        jPanel21.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        SearchButtonForMemberId.setBackground(new java.awt.Color(255, 204, 102));
        SearchButtonForMemberId.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        SearchButtonForMemberId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsFor-LibrarianModule/icons8-search-24.png"))); // NOI18N
        SearchButtonForMemberId.setText("Search");
        SearchButtonForMemberId.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SearchButtonForMemberId.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        SearchButtonForMemberId.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        SearchButtonForMemberId.setIconTextGap(2);
        SearchButtonForMemberId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonForMemberIdActionPerformed(evt);
            }
        });
        jPanel21.add(SearchButtonForMemberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 90, -1));

        txt_MemberId_BookTransac.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txt_MemberId_BookTransac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_MemberId_BookTransac.setSelectionStart(2);
        txt_MemberId_BookTransac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemberId_BookTransacActionPerformed(evt);
            }
        });
        jPanel21.add(txt_MemberId_BookTransac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 260, 30));

        jPanel10.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 500, 330));

        buttonforIssueBookOfBookTransaction.setBackground(new java.awt.Color(39, 80, 112));
        buttonforIssueBookOfBookTransaction.setText("Issue Book");
        buttonforIssueBookOfBookTransaction.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonforIssueBookOfBookTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonforIssueBookOfBookTransactionActionPerformed(evt);
            }
        });
        jPanel10.add(buttonforIssueBookOfBookTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 200, 60));

        txt_Penalty.setBackground(new java.awt.Color(255, 243, 136));
        txt_Penalty.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Penalty.setText("15.00");
        txt_Penalty.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_Penalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PenaltyActionPerformed(evt);
            }
        });
        jPanel10.add(txt_Penalty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 442, 40, 30));

        jLabel56.setBackground(new java.awt.Color(241, 184, 20));
        jLabel56.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel56.setText("NO. of Book/s to be borrowed : ");
        jPanel10.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 240, 31));

        txt_BooksToBeBorrowed.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_BooksToBeBorrowed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_BooksToBeBorrowed.setSelectionStart(2);
        txt_BooksToBeBorrowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BooksToBeBorrowedActionPerformed(evt);
            }
        });
        jPanel10.add(txt_BooksToBeBorrowed, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 90, 30));

        jLabel57.setBackground(new java.awt.Color(241, 184, 20));
        jLabel57.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel57.setText("Max Day/s Allowed:");
        jPanel10.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 150, 31));

        txt_MaxDaysAllowed.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_MaxDaysAllowed.setText("1");
        txt_MaxDaysAllowed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_MaxDaysAllowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaxDaysAllowedActionPerformed(evt);
            }
        });
        jPanel10.add(txt_MaxDaysAllowed, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 395, 80, 27));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1070, 560));

        jScrollPane7.setViewportView(jPanel9);

        bookTransactionCardLayout.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 720));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel62.setText("---");
        bookTransactionCardLayout.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 200, -1));

        Parent.add(bookTransactionCardLayout, "card4");

        memberMaintenanceCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crud_tableDataOfmembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Member ID   ", "First Name", "Middle Name ", "Last Name", "Suffix", "Section  ", "School Year  ", "Contact   ", "Gender   ", "Member Type ", "Grade Level  ", "Strand   "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        crud_tableDataOfmembers.setAltoHead(27);
        crud_tableDataOfmembers.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfmembers.setColorBordeFilas(new java.awt.Color(204, 204, 204));
        crud_tableDataOfmembers.setColorBordeHead(new java.awt.Color(255, 255, 255));
        crud_tableDataOfmembers.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        crud_tableDataOfmembers.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        crud_tableDataOfmembers.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        crud_tableDataOfmembers.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        crud_tableDataOfmembers.setColorSelBackgound(new java.awt.Color(79, 126, 171));
        crud_tableDataOfmembers.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        crud_tableDataOfmembers.setFuenteHead(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        crud_tableDataOfmembers.setGrosorBordeFilas(0);
        crud_tableDataOfmembers.setGrosorBordeHead(0);
        crud_tableDataOfmembers.setMaximumSize(new java.awt.Dimension(2147483647, 775));
        crud_tableDataOfmembers.setMinimumSize(new java.awt.Dimension(160, 780));
        crud_tableDataOfmembers.setPreferredSize(new java.awt.Dimension(860, 780));
        crud_tableDataOfmembers.setShowGrid(true);
        crud_tableDataOfmembers.setSurrendersFocusOnKeystroke(true);
        crud_tableDataOfmembers.getTableHeader().setReorderingAllowed(false);
        crud_tableDataOfmembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfmembersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(crud_tableDataOfmembers);
        if (crud_tableDataOfmembers.getColumnModel().getColumnCount() > 0) {
            crud_tableDataOfmembers.getColumnModel().getColumn(0).setPreferredWidth(65);
            crud_tableDataOfmembers.getColumnModel().getColumn(1).setPreferredWidth(110);
            crud_tableDataOfmembers.getColumnModel().getColumn(2).setPreferredWidth(110);
            crud_tableDataOfmembers.getColumnModel().getColumn(3).setPreferredWidth(110);
            crud_tableDataOfmembers.getColumnModel().getColumn(4).setPreferredWidth(35);
            crud_tableDataOfmembers.getColumnModel().getColumn(5).setPreferredWidth(77);
            crud_tableDataOfmembers.getColumnModel().getColumn(6).setPreferredWidth(75);
            crud_tableDataOfmembers.getColumnModel().getColumn(7).setPreferredWidth(70);
            crud_tableDataOfmembers.getColumnModel().getColumn(8).setPreferredWidth(50);
            crud_tableDataOfmembers.getColumnModel().getColumn(9).setPreferredWidth(85);
            crud_tableDataOfmembers.getColumnModel().getColumn(10).setPreferredWidth(73);
            crud_tableDataOfmembers.getColumnModel().getColumn(11).setPreferredWidth(39);
        }

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1090, 590));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        firstname_label.setBackground(new java.awt.Color(241, 184, 20));
        firstname_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        firstname_label.setText("First Name");
        jPanel19.add(firstname_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 31));

        txt_Memberfirstname.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Memberfirstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Memberfirstname.setSelectionStart(2);
        jPanel19.add(txt_Memberfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 25));

        middlename_label.setBackground(new java.awt.Color(241, 184, 20));
        middlename_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        middlename_label.setText("Middle Name");
        jPanel19.add(middlename_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, 30));

        txt_Membermiddlename.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Membermiddlename.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membermiddlename.setSelectionStart(2);
        jPanel19.add(txt_Membermiddlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 230, 25));

        lastname_label.setBackground(new java.awt.Color(241, 184, 20));
        lastname_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        lastname_label.setText("Suffix*");
        jPanel19.add(lastname_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, -1, 30));

        txtsuffix.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtsuffix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtsuffix.setSelectionStart(2);
        jPanel19.add(txtsuffix, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 100, 25));

        jComboBox_Membergender.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBox_Membergender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel19.add(jComboBox_Membergender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 25));

        membertype_label.setBackground(new java.awt.Color(241, 184, 20));
        membertype_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        membertype_label.setText("Member Type");
        jPanel19.add(membertype_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, 31));

        jComboBox_strand.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBox_strand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "ABM", "HUMSS", "ICT", "STEM" }));
        jComboBox_strand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_strandActionPerformed(evt);
            }
        });
        jPanel19.add(jComboBox_strand, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 210, 25));

        gradelevel_label.setBackground(new java.awt.Color(241, 184, 20));
        gradelevel_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        gradelevel_label.setText("Grade Level");
        jPanel19.add(gradelevel_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, 30));

        sectionstrand_label.setBackground(new java.awt.Color(241, 184, 20));
        sectionstrand_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        sectionstrand_label.setText("Strand*");
        jPanel19.add(sectionstrand_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, 30));

        contact_label.setBackground(new java.awt.Color(241, 184, 20));
        contact_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        contact_label.setText("Contact");
        jPanel19.add(contact_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 60, 30));

        txt_Membercontact.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Membercontact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membercontact.setSelectionStart(2);
        jPanel19.add(txt_Membercontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 230, 25));

        schoolyear_label.setBackground(new java.awt.Color(241, 184, 20));
        schoolyear_label.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        schoolyear_label.setText("Section");
        jPanel19.add(schoolyear_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        txt_Membersection.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Membersection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membersection.setSelectionStart(2);
        jPanel19.add(txt_Membersection, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 230, 25));

        schoolyear_label1.setBackground(new java.awt.Color(241, 184, 20));
        schoolyear_label1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        schoolyear_label1.setText("School Year");
        jPanel19.add(schoolyear_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, 30));

        jComboBox_gradelevel.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBox_gradelevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11", "12" }));
        jComboBox_gradelevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_gradelevelActionPerformed(evt);
            }
        });
        jPanel19.add(jComboBox_gradelevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 210, 25));

        jComboBox_membertype.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBox_membertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Faculty", "Staff" }));
        jPanel19.add(jComboBox_membertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 170, 25));

        gender_label2.setBackground(new java.awt.Color(241, 184, 20));
        gender_label2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        gender_label2.setText("Gender");
        jPanel19.add(gender_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 60, 30));

        try {
            formattedtxt_schoolyear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        formattedtxt_schoolyear.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel19.add(formattedtxt_schoolyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 230, 25));

        crud_MemberAdd.setBackground(new java.awt.Color(39, 80, 112));
        crud_MemberAdd.setText("add");
        crud_MemberAdd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_MemberAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberAddActionPerformed(evt);
            }
        });
        jPanel19.add(crud_MemberAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 140, 50));

        crud_MemberupdateBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_MemberupdateBtn.setText("UPDATE");
        crud_MemberupdateBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_MemberupdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberupdateBtnActionPerformed(evt);
            }
        });
        jPanel19.add(crud_MemberupdateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 140, 50));

        crud_MemberdeleteBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_MemberdeleteBtn.setText("DELETE");
        crud_MemberdeleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_MemberdeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberdeleteBtnActionPerformed(evt);
            }
        });
        jPanel19.add(crud_MemberdeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 140, 50));

        crud_MemberclearBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_MemberclearBtn.setText("CLEAR");
        crud_MemberclearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_MemberclearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberclearBtnActionPerformed(evt);
            }
        });
        jPanel19.add(crud_MemberclearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 140, 50));

        txt_Memberlastname.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_Memberlastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Memberlastname.setSelectionStart(2);
        jPanel19.add(txt_Memberlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 230, 25));

        lastname_label1.setBackground(new java.awt.Color(241, 184, 20));
        lastname_label1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        lastname_label1.setText("Last Name");
        jPanel19.add(lastname_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, 30));

        jPanel8.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1090, 300));

        labelsearchmemberid.setBackground(new java.awt.Color(241, 184, 20));
        labelsearchmemberid.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        labelsearchmemberid.setText("Search Member ID:");
        jPanel8.add(labelsearchmemberid, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 150, 40));

        txtSearchmemberidForMemberMaint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtSearchmemberidForMemberMaint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSearchmemberidForMemberMaint.setSelectionStart(2);
        txtSearchmemberidForMemberMaint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchmemberidForMemberMaintKeyReleased(evt);
            }
        });
        jPanel8.add(txtSearchmemberidForMemberMaint, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 350, 30));

        jScrollPane6.setViewportView(jPanel8);

        memberMaintenanceCardLayout.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 1010));

        Parent.add(memberMaintenanceCardLayout, "card5");

        bookMaintenanceCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setBackground(new java.awt.Color(241, 184, 20));
        jLabel48.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel48.setText("Status");
        jPanel20.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, 40));

        txt_author.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_author.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_author.setSelectionStart(2);
        txt_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorActionPerformed(evt);
            }
        });
        jPanel20.add(txt_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 177, 230, 27));

        txt_booktitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_booktitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle.setSelectionStart(2);
        txt_booktitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitleActionPerformed(evt);
            }
        });
        jPanel20.add(txt_booktitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 117, 230, 27));

        txt_category.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_category.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_category.setSelectionStart(2);
        txt_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_categoryActionPerformed(evt);
            }
        });
        jPanel20.add(txt_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 316, 230, 27));

        jLabel15.setBackground(new java.awt.Color(241, 184, 20));
        jLabel15.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel15.setText("Book Title");
        jPanel20.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 101, -1, 20));

        jLabel16.setBackground(new java.awt.Color(241, 184, 20));
        jLabel16.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel16.setText("Author");
        jPanel20.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        jLabel18.setBackground(new java.awt.Color(241, 184, 20));
        jLabel18.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel18.setText("Publisher");
        jPanel20.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 30));

        jComboBox_statusBook.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBox_statusBook.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jComboBox_statusBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_statusBookActionPerformed(evt);
            }
        });
        jPanel20.add(jComboBox_statusBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 150, 30));

        crud_addBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_addBtn.setText("add");
        crud_addBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_addBtnActionPerformed(evt);
            }
        });
        jPanel20.add(crud_addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 110, 50));

        crud_deleteBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_deleteBtn.setText("DELETE");
        crud_deleteBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_deleteBtnActionPerformed(evt);
            }
        });
        jPanel20.add(crud_deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 110, 50));

        crud_tableDataOfBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id   ", "Book Title   ", "Author   ", "Publisher   ", "Category   ", "Quantity   ", "Status    "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        crud_tableDataOfBooks.setAltoHead(30);
        crud_tableDataOfBooks.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfBooks.setColorBordeFilas(new java.awt.Color(204, 204, 204));
        crud_tableDataOfBooks.setColorBordeHead(new java.awt.Color(255, 255, 255));
        crud_tableDataOfBooks.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        crud_tableDataOfBooks.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        crud_tableDataOfBooks.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        crud_tableDataOfBooks.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        crud_tableDataOfBooks.setColorSelBackgound(new java.awt.Color(79, 126, 171));
        crud_tableDataOfBooks.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        crud_tableDataOfBooks.setFuenteHead(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        crud_tableDataOfBooks.setGrosorBordeFilas(0);
        crud_tableDataOfBooks.setGrosorBordeHead(0);
        crud_tableDataOfBooks.setMinimumSize(new java.awt.Dimension(100, 580));
        crud_tableDataOfBooks.setRowHeight(18);
        crud_tableDataOfBooks.setSelectionBackground(new java.awt.Color(102, 102, 255));
        crud_tableDataOfBooks.setShowGrid(true);
        crud_tableDataOfBooks.getTableHeader().setReorderingAllowed(false);
        crud_tableDataOfBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfBooksMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(crud_tableDataOfBooks);
        if (crud_tableDataOfBooks.getColumnModel().getColumnCount() > 0) {
            crud_tableDataOfBooks.getColumnModel().getColumn(0).setPreferredWidth(10);
            crud_tableDataOfBooks.getColumnModel().getColumn(5).setMinWidth(15);
            crud_tableDataOfBooks.getColumnModel().getColumn(5).setPreferredWidth(17);
            crud_tableDataOfBooks.getColumnModel().getColumn(6).setHeaderValue("Status    ");
        }

        jPanel20.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 830, 560));

        jLabel21.setBackground(new java.awt.Color(241, 184, 20));
        jLabel21.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel21.setText("Quantity");
        jPanel20.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 30));

        jLabel23.setBackground(new java.awt.Color(241, 184, 20));
        jLabel23.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        jLabel23.setText("Category");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 40));

        txt_quantity.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_quantity.setSelectionStart(2);
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        jPanel20.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 393, 230, 27));

        txt_publisher.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_publisher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_publisher.setSelectionStart(2);
        txt_publisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_publisherActionPerformed(evt);
            }
        });
        jPanel20.add(txt_publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 242, 230, 27));

        crud_clearBtn.setBackground(new java.awt.Color(39, 80, 112));
        crud_clearBtn.setText("CLEAR");
        crud_clearBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_clearBtnActionPerformed(evt);
            }
        });
        jPanel20.add(crud_clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 110, 50));

        labelsearchbooktitle.setBackground(new java.awt.Color(241, 184, 20));
        labelsearchbooktitle.setFont(new java.awt.Font("Yu Gothic Medium", 0, 16)); // NOI18N
        labelsearchbooktitle.setText("Search Book Title: ");
        jPanel20.add(labelsearchbooktitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 150, 50));

        txtSearchBookTitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtSearchBookTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSearchBookTitle.setSelectionStart(2);
        txtSearchBookTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBookTitleActionPerformed(evt);
            }
        });
        txtSearchBookTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchBookTitleKeyReleased(evt);
            }
        });
        jPanel20.add(txtSearchBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 37, 350, 25));

        crud_updateBtn2.setBackground(new java.awt.Color(39, 80, 112));
        crud_updateBtn2.setText("UPDATE");
        crud_updateBtn2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        crud_updateBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_updateBtn2ActionPerformed(evt);
            }
        });
        jPanel20.add(crud_updateBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 110, 50));

        jScrollPane1.setViewportView(jPanel20);

        bookMaintenanceCardLayout.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 720));

        Parent.add(bookMaintenanceCardLayout, "card6");

        getContentPane().add(Parent, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 1120, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            LoginFormCLS logForm = new LoginFormCLS();
            logForm.setFocusable(true);
            logForm.show();

            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        jPanel16.setBackground(mouseClickColor);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        jPanel16.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel2MouseReleased

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        jPanel12.setBackground(mouseClickColor);
    }//GEN-LAST:event_jLabel17MousePressed

    private void jLabel17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseReleased
        jPanel12.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel17MouseReleased

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        jPanel13.setBackground(mouseClickColor);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        jPanel13.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        jPanel7.setBackground(mouseClickColor);
    }//GEN-LAST:event_jLabel10MousePressed

    private void jLabel10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseReleased
        jPanel7.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel10MouseReleased

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
        jPanel6.setBackground(mouseClickColor);
    }//GEN-LAST:event_jLabel14MousePressed

    private void jLabel14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseReleased
        jPanel6.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel14MouseReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Parent.removeAll();
        Parent.add(dashboardCardLayout);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        Parent.removeAll();
        Parent.add(listOfIssuedBooksCardLayout);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Parent.removeAll();
        Parent.add(bookTransactionCardLayout);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        Parent.removeAll();
        Parent.add(memberMaintenanceCardLayout);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Parent.removeAll();
        Parent.add(bookMaintenanceCardLayout);
        Parent.repaint();
        Parent.revalidate();
    }//GEN-LAST:event_jLabel14MouseClicked

    // Initialize a variable to store the ID of the selected book
    private void crud_clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_clearBtnActionPerformed
        clearFields();
    }//GEN-LAST:event_crud_clearBtnActionPerformed

    private void txt_publisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_publisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_publisherActionPerformed

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void crud_tableDataOfBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfBooksMouseClicked
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfBooks.getModel();
        int index = crud_tableDataOfBooks.getSelectedRow();

        // Check if the selected row is not the first row
        // Get the ID of the selected book
        idN = Integer.valueOf(model.getValueAt(index, 0).toString());

        txt_booktitle.setText(model.getValueAt(index, 1).toString());
        txt_author.setText(model.getValueAt(index, 2).toString());
        txt_publisher.setText(model.getValueAt(index, 3).toString());
        txt_category.setText(model.getValueAt(index, 4).toString());
        txt_quantity.setText(model.getValueAt(index, 5).toString());
        jComboBox_statusBook.setSelectedItem(model.getValueAt(index, 6).toString());
        showBookDataToTable();
        displaynoOfUnAvailableBooks();
        displaynoOfAvailableBooks();
        displayTotalOfBooks();


    }//GEN-LAST:event_crud_tableDataOfBooksMouseClicked

    private void crud_deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_deleteBtnActionPerformed
        // SQL command to delete the book data with the corresponding id number
        String deleteData = "DELETE FROM book_details WHERE book_id=" + String.valueOf(idN);
        // SQL command to update the book id numbers for the remaining records
        String updateIds = "UPDATE book_details SET book_id = book_id - 1 WHERE book_id > ?";

        try {
            if (txt_booktitle.getText().isEmpty() || txt_author.getText().isEmpty() || txt_publisher.getText().isEmpty() || txt_category.getText().isEmpty() || txt_quantity.getText().isEmpty() || jComboBox_statusBook.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a row first", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to DELETE? ", "Confirmation Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    con = Connectionz.getConnection();
                    pst = con.prepareStatement(deleteData);
                    pst.executeUpdate();
                    pst.close();

                    pst = con.prepareStatement(updateIds);
                    pst.setInt(1, idN);
                    pst.executeUpdate();
                    pst.close();

                    JOptionPane.showMessageDialog(this, "Successfully Deleted", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                    //TO SHOW UPDATED DATA
                    displaynoOfUnAvailableBooks();
                    showBookDataToTable();
                    displaynoOfAvailableBooks();
                    displayTotalOfBooks();

                    //CLEAR FIELDS
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cancelled deletion!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_crud_deleteBtnActionPerformed

    private void crud_addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_addBtnActionPerformed
        // Display the current book data in the JTable
        showBookDataToTable();

        // SQL statement to get the maximum book ID from the book_details table
        String getMaxId = "SELECT MAX(book_id) FROM book_details";
        // SQL statement to insert book data into the book_details table
        String insertData = "INSERT INTO book_details (book_id, book_title, author, publisher, category, quantity, status) VALUES (?,?,?,?,?,?,?)";
        // SQL statement to check if a book with the same book_title, author, publisher, category, and status already exists
        String checkExistingBook = "SELECT COUNT(*) FROM book_details WHERE book_title=? AND author=? AND publisher=? AND category=? AND status=?";

        // Get a connection to the database
        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields have been filled out, otherwise show an error message
            if (txt_booktitle.getText().isEmpty() || txt_author.getText().isEmpty() || txt_publisher.getText().isEmpty() || txt_category.getText().isEmpty() || txt_quantity.getText().isEmpty() || jComboBox_statusBook.getSelectedItem() == null) {
                if (!txt_booktitle.hasFocus() || !txt_author.hasFocus() || !txt_publisher.hasFocus() || !txt_category.hasFocus() || !txt_quantity.hasFocus() || !jComboBox_statusBook.hasFocus()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all necessary information", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Validate if the input for quantity is a number
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(txt_quantity.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number", "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if a book with the same book_title, author, publisher, category, and status already exists
                pst = con.prepareStatement(checkExistingBook);
                pst.setString(1, txt_booktitle.getText());
                pst.setString(2, txt_author.getText());
                pst.setString(3, txt_publisher.getText());
                pst.setString(4, txt_category.getText());
                pst.setString(5, (String) jComboBox_statusBook.getSelectedItem());
                ResultSet rs = pst.executeQuery();
                int existingBookCount = rs.next() ? rs.getInt(1) : 0; // Get the count of existing books
                if (existingBookCount > 0) {
                    // If a book with the same details already exists, show an error message
                    JOptionPane.showMessageDialog(this, "A book with the same details already exists", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the maximum book ID from the database and increment it by 1 to generate a new, unique ID for the book being added
                    pst = con.prepareStatement(getMaxId);
                    rs = pst.executeQuery();
                    int maxId = rs.next() ? rs.getInt(1) : 0; // If there are no existing book IDs, use the default value of 0 as the maximum
                    int newId = maxId + 1;

                    // Insert the book data into the book_details table
                    pst = con.prepareStatement(insertData);
                    pst.setInt(1, newId);
                    pst.setString(2, txt_booktitle.getText());
                    pst.setString(3, txt_author.getText());
                    pst.setString(4, txt_publisher.getText());
                    pst.setString(5, txt_category.getText());
                    pst.setString(6, txt_quantity.getText());
                    pst.setString(7, (String) jComboBox_statusBook.getSelectedItem());
                    int result = pst.executeUpdate();
                    if (result > 0) {
                        // If the insertion is successful, show a success message
                        JOptionPane.showMessageDialog(this, "Book added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Clear the input fields and refresh the JTable

                        clearFields();
                        showBookDataToTable();
                        displaynoOfAvailableBooks();
                        displaynoOfUnAvailableBooks();
                        displayTotalOfBooks();
                    } else {
                        // If the insertion fails, show an error message
                        JOptionPane.showMessageDialog(this, "Failed to add book", "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_crud_addBtnActionPerformed

    private void jComboBox_statusBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_statusBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_statusBookActionPerformed

    private void txt_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_categoryActionPerformed

    private void txt_booktitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitleActionPerformed

    private void txt_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorActionPerformed

    private void txt_BookTitle_BookTransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookTitle_BookTransacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookTitle_BookTransacActionPerformed

    private void crud_MemberAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberAddActionPerformed
        showMemberDataToTable();

// SQL statement to get the maximum member ID from the memberinfo_table
        String getMaxId = "SELECT MAX(member_id) FROM memberinfo_table";
// SQL statement to insert member data into the memberinfo_table
        String insertData = "INSERT INTO memberinfo_table (member_id,first_name,middle_name,last_name,suffix,section,school_year,contact,gender,member_type,grade_level,strand) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
// SQL statement to check for duplicates
        String checkDuplicate = "SELECT * FROM memberinfo_table WHERE first_name=? AND middle_name=? AND last_name=? AND suffix=? AND contact=?";

// Get a connection to the database
        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields have been filled out, otherwise show an error message
            if (txt_Memberfirstname.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
                if (!txt_Memberfirstname.hasFocus() || !txt_Memberlastname.hasFocus() || !jComboBox_Membergender.hasFocus() || !jComboBox_membertype.hasFocus() || !jComboBox_gradelevel.hasFocus() || !jComboBox_strand.hasFocus()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all necessary information", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Check for duplicates
                pst = con.prepareStatement(checkDuplicate);
                pst.setString(1, txt_Memberfirstname.getText());
                pst.setString(2, txt_Membermiddlename.getText());
                pst.setString(3, txt_Memberlastname.getText());
                pst.setString(4, txtsuffix.getText());
                pst.setString(5, txt_Membercontact.getText());
                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "A member with the same first name, last name, suffix , and contact number already exists", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Validate if the input for school year is a number
                    try {
                        String schoolyearString = formattedtxt_schoolyear.getText();
                        String[] schoolyearArray = schoolyearString.split("-");

                        if (schoolyearArray.length != 2) {
                            throw new NumberFormatException();
                        }

                        int schoolyearStart = Integer.parseInt(schoolyearArray[0]);
                        int schoolyearEnd = Integer.parseInt(schoolyearArray[1]);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "School Year must be in the format YYYY-YYYY and must be a number", "Error Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Get the maximum member ID from the database and increment it by 1 to generate a new, unique ID for the member being added
                    pst = con.prepareStatement(getMaxId);
                    ResultSet rs = pst.executeQuery();
                    int maxId = rs.next() ? rs.getInt(1) : 0; // If there are no existing member IDs, use the default value of 0 as the maximum
                    int newId = maxId + 1;
                    // Insert the new member data into the database
                    pst = con.prepareStatement(insertData);
                    pst.setInt(1, newId);
                    pst.setString(2, txt_Memberfirstname.getText());
                    pst.setString(3, txt_Membermiddlename.getText());
                    pst.setString(4, txt_Memberlastname.getText());
                    pst.setString(5, txtsuffix.getText());
                    pst.setString(6, txt_Membersection.getText());
                    pst.setString(7, formattedtxt_schoolyear.getText());
                    pst.setString(8, txt_Membercontact.getText());
                    pst.setString(9, jComboBox_Membergender.getSelectedItem().toString());
                    pst.setString(10, jComboBox_membertype.getSelectedItem().toString());
                    pst.setString(11, jComboBox_gradelevel.getSelectedItem().toString());
                    pst.setString(12, jComboBox_strand.getSelectedItem().toString());

                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Member successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showMemberDataToTable();
                        clearFieldsForMember();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add member", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();

        }
    }//GEN-LAST:event_crud_MemberAddActionPerformed

    private void crud_tableDataOfmembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfmembersMouseClicked
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfmembers.getModel();
        int index = crud_tableDataOfmembers.getSelectedRow();

        // Get the ID of the selected book
        // Check if the selected row is not the first row
        idN = Integer.valueOf(model.getValueAt(index, 0).toString());

        txt_Memberfirstname.setText(model.getValueAt(index, 1).toString());
        txt_Membermiddlename.setText(model.getValueAt(index, 2).toString());
        txt_Memberlastname.setText(model.getValueAt(index, 3).toString());
        txtsuffix.setText(model.getValueAt(index, 4).toString());
        txt_Membersection.setText(model.getValueAt(index, 5).toString());
        formattedtxt_schoolyear.setText(model.getValueAt(index, 6).toString());
        txt_Membercontact.setText(model.getValueAt(index, 7).toString());
        jComboBox_Membergender.setSelectedItem(model.getValueAt(index, 8).toString());
        jComboBox_membertype.setSelectedItem(model.getValueAt(index, 9).toString());
        jComboBox_gradelevel.setSelectedItem(model.getValueAt(index, 10).toString());
        jComboBox_strand.setSelectedItem(model.getValueAt(index, 11).toString());

    }//GEN-LAST:event_crud_tableDataOfmembersMouseClicked

    private void crud_MemberupdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberupdateBtnActionPerformed
        // When the Update button is clicked, prepare the SQL statement with the new data

        String updateData = "UPDATE memberinfo_table SET first_name='"
                + txt_Memberfirstname.getText() + "', middle_name='"
                + txt_Membermiddlename.getText() + "', last_name='"
                + txt_Memberlastname.getText() + "', suffix='"
                + txtsuffix.getText() + "', section='"
                + txt_Membersection.getText() + "', school_year='"
                + formattedtxt_schoolyear.getText() + "', contact='"
                + txt_Membercontact.getText() + "', gender='"
                + jComboBox_Membergender.getSelectedItem() + "', member_type='"
                + jComboBox_membertype.getSelectedItem() + "', grade_level='"
                + jComboBox_gradelevel.getSelectedItem() + "', strand='"
                + jComboBox_strand.getSelectedItem() + "' WHERE member_id=" + idN;

        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields are filled
            if (txt_Memberfirstname.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a row first", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if contact is a number

                if (!txt_Membercontact.getText().trim().isEmpty() && !txt_Membercontact.getText().trim().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Contact Number must not contain a letter", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Confirm with user if they want to update data
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to UPDATE? ", "Confirmation Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        pst = con.prepareStatement(updateData);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                        //TO SHOW UPDATED DATA
                        showMemberDataToTable();

                        //CLEAR FIELDS
                        clearFieldsForMember();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cancelled update!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_crud_MemberupdateBtnActionPerformed

    private void crud_MemberdeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberdeleteBtnActionPerformed
        String deleteData = "DELETE FROM memberinfo_table WHERE member_id=" + String.valueOf(idN);
        // SQL command to update the book id numbers for the remaining records
        String updateIds = "UPDATE memberinfo_table SET member_id = member_id - 1 WHERE member_id > ?";

        try {
            if (txt_Memberfirstname.getText().isEmpty() || txt_Membermiddlename.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || txt_Membersection.getText().isEmpty() || formattedtxt_schoolyear.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a row first", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to DELETE? ", "Confirmation Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    con = Connectionz.getConnection();
                    pst = con.prepareStatement(deleteData);
                    pst.executeUpdate();
                    pst.close();

                    pst = con.prepareStatement(updateIds);
                    pst.setInt(1, idN);
                    pst.executeUpdate();
                    pst.close();

                    JOptionPane.showMessageDialog(this, "Successfully Deleted", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                    //TO SHOW UPDATED DATA
                    showMemberDataToTable();

                    //CLEAR FIELDS
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cancelled deletion!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_crud_MemberdeleteBtnActionPerformed

    private void crud_MemberclearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberclearBtnActionPerformed
        clearFieldsForMember();
    }//GEN-LAST:event_crud_MemberclearBtnActionPerformed

    private void txt_MemberId_BookTransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemberId_BookTransacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemberId_BookTransacActionPerformed

    private void SearchButtonForBookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonForBookIdActionPerformed

        if (txt_BookTitle_BookTransac.getText().isEmpty()) {

            BookAuthorLabelForBookDetails.setText("---");
            BookCategoryLabelForBookDetails.setText("---");
            BookQuantitylabelForBookDetails.setText("---");
            JOptionPane.showMessageDialog(new JFrame(), "The fields cannot be left blank.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {
                ResultSet rs;
                PreparedStatement ps = con.prepareStatement("SELECT  author, category, quantity FROM book_details WHERE book_title = ?;");
                ps.setString(1, txt_BookTitle_BookTransac.getText());
                rs = ps.executeQuery();

                while (true) {
                    if (rs.next()) {
                        String AUTHOR = rs.getString("author");
                        BookAuthorLabelForBookDetails.setText(AUTHOR);
                        String CATEGORY = rs.getString("category");
                        BookCategoryLabelForBookDetails.setText(CATEGORY);
                        String QUANTITY = rs.getString("quantity");
                        BookQuantitylabelForBookDetails.setText(QUANTITY);
                        break;
                    } else {
                        BookAuthorLabelForBookDetails.setText("---");
                        BookCategoryLabelForBookDetails.setText("---");
                        BookQuantitylabelForBookDetails.setText("---");
                        JOptionPane.showMessageDialog(new JFrame(), "Book Not Found in Database.", "Message", JOptionPane.INFORMATION_MESSAGE);
                        txt_BookTitle_BookTransac.setText("");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_SearchButtonForBookIdActionPerformed

    private void SearchButtonForMemberIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonForMemberIdActionPerformed

        if (txt_MemberId_BookTransac.getText().isEmpty()) {
            MemberNameLabelForStudentDetails.setText("---");
            MemberTypeLabelForStudentDetails.setText("---");
            MemberStrandLabelForStudentDetails.setText("---");
            MemberSectionLabelForStudentDetails.setText("---");
            JOptionPane.showMessageDialog(new JFrame(), "The fields cannot be left blank.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {
                ResultSet rs;
                PreparedStatement ps = con.prepareStatement("SELECT member_id, first_name, middle_name, last_name,suffix, member_type, strand, section FROM memberinfo_table WHERE member_id = ?;");
                ps.setString(1, txt_MemberId_BookTransac.getText());
                rs = ps.executeQuery();

                while (true) {
                    if (rs.next()) {

                        String firstName = rs.getString("first_name");
                        String middleName = rs.getString("middle_name");
                        String lastName = rs.getString("last_name");
                        String Suffix = rs.getString("suffix");
                        // Concatenating the first_name, middle_name, and last_name into a full name
                        String fullName = firstName + " " + middleName + " " + lastName + " " + Suffix;
                        MemberNameLabelForStudentDetails.setText(fullName);

                        String MEMBERTYPE = rs.getString("member_type");
                        MemberTypeLabelForStudentDetails.setText(MEMBERTYPE);
                        String STRAND = rs.getString("strand");
                        MemberStrandLabelForStudentDetails.setText(STRAND);
                        String SECTION = rs.getString("section");
                        MemberSectionLabelForStudentDetails.setText(SECTION);
                        break;
                    } else {
                        MemberNameLabelForStudentDetails.setText("---");
                        MemberTypeLabelForStudentDetails.setText("---");
                        MemberStrandLabelForStudentDetails.setText("---");
                        MemberSectionLabelForStudentDetails.setText("---");
                        JOptionPane.showMessageDialog(new JFrame(), "Student Not Found in Database.", "Message", JOptionPane.INFORMATION_MESSAGE);
                        txt_MemberId_BookTransac.setText("");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_SearchButtonForMemberIdActionPerformed

    private void txt_PenaltyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PenaltyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PenaltyActionPerformed

    private void txt_BooksToBeBorrowedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BooksToBeBorrowedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BooksToBeBorrowedActionPerformed

    private void txt_MaxDaysAllowedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaxDaysAllowedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaxDaysAllowedActionPerformed

    private void buttonforIssueBookOfBookTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonforIssueBookOfBookTransactionActionPerformed

        if (txt_BookTitle_BookTransac.getText().isEmpty() || txt_MemberId_BookTransac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Please search for a book and a member first.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String maxDaysAllowed = txt_MaxDaysAllowed.getText();
            String penalty = txt_Penalty.getText();
            String booksToBeBorrowed = txt_BooksToBeBorrowed.getText();
            if (maxDaysAllowed.isEmpty() || penalty.isEmpty() || booksToBeBorrowed.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Please enter all required information.", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    // Retrieve book information
                    String fetchBookSql = "SELECT book_id, quantity FROM book_details WHERE book_title = ?";
                    pst = con.prepareStatement(fetchBookSql);
                    pst.setString(1, txt_BookTitle_BookTransac.getText());
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        PreparedStatement pst = con.prepareStatement("UPDATE book_details SET quantity = ? WHERE book_title = ?");
                        int quantity = rs.getInt("quantity");
                        int booksToBorrow = Integer.parseInt(booksToBeBorrowed);
                        if (quantity >= booksToBorrow) {
                            quantity -= booksToBorrow;
                            pst = con.prepareStatement("UPDATE book_details SET quantity = ? WHERE book_title = ?");
                            pst.setInt(1, quantity);
                            pst.setString(2, txt_BookTitle_BookTransac.getText());
                            pst.executeUpdate();
                            BookQuantitylabelForBookDetails.setText(Integer.toString(quantity));
                            // Retrieve member information
                            String fetchMemberSql = "SELECT * FROM memberinfo_table WHERE member_id = ?";
                            pst = con.prepareStatement(fetchMemberSql);
                            String MEMBERID = txt_MemberId_BookTransac.getText();
                            pst.setString(1, MEMBERID);
                            rs = pst.executeQuery();
                            if (rs.next()) {
                                String firstName = rs.getString("first_name");
                                String middleName = rs.getString("middle_name");
                                String lastName = rs.getString("last_name");
                                String Suffix = rs.getString("suffix");
                                String fullName = firstName + " " + middleName + " " + lastName + " " + Suffix;
                                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                LocalDateTime currentDate = LocalDateTime.now();
                                LocalDateTime dueDate = currentDate.plusDays(Integer.parseInt(maxDaysAllowed));
                                double penaltyAmount = 0.00;
                                if (LocalDateTime.now().isAfter(dueDate)) {
                                    long daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDateTime.now());
                                    penaltyAmount = Double.parseDouble(penalty) * daysOverdue;
                                }
                                // Insert book issue information into database
                                String sql = "INSERT INTO issued_books ( book_title, category, member_id, member_name, member_type, date_borrowed, due_date, penalty, books_borrowed,Status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                                pst = con.prepareStatement(sql);
                                pst.setString(1, txt_BookTitle_BookTransac.getText());
                                pst.setString(2, BookCategoryLabelForBookDetails.getText());
                                pst.setString(3, MEMBERID);
                                pst.setString(4, fullName);
                                pst.setString(5, MemberTypeLabelForStudentDetails.getText());
                                pst.setString(6, dateFormat.format(currentDate));
                                pst.setString(7, dateFormat.format(dueDate));
                                pst.setDouble(8, penaltyAmount);
                                pst.setString(9, booksToBeBorrowed);
                                pst.setString(10, "pending");
                                pst.executeUpdate();

                                // Update data tables
                                showBookDataToTable();
                                showIssuedBooksDataToTable();
                                displaynoOfBorrowers();
                                displaytotalofnoIssuedBooks();
                                JOptionPane.showMessageDialog(new JFrame(), "Book(s) issued successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Member not found.", "Message", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Not enough books available.", "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Book not found.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error: " + ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_buttonforIssueBookOfBookTransactionActionPerformed

    private void txtSearchBookTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBookTitleKeyReleased
        String searchBookTitle = txtSearchBookTitle.getText();
        searchcrud_tableDataOfBooks(searchBookTitle);
    }//GEN-LAST:event_txtSearchBookTitleKeyReleased

    private void txtSearchmemberidForMemberMaintKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchmemberidForMemberMaintKeyReleased
        String searchmemberidmaintenance = txtSearchmemberidForMemberMaint.getText();
        searchcrud_tableDataOfmembers(searchmemberidmaintenance);
    }//GEN-LAST:event_txtSearchmemberidForMemberMaintKeyReleased

    private void buttonforReturnBookInListOfIssuedBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonforReturnBookInListOfIssuedBooksActionPerformed
        int selectedRow = tableDataOfListOfIssuedBooks.getSelectedRow();

        // Display an error message if no row is selected
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row.");
            return;
        }

        // Get the book title, member ID, and number of books borrowed
        String bookTitle = tableDataOfListOfIssuedBooks.getValueAt(selectedRow, 0).toString();
        String memberID = tableDataOfListOfIssuedBooks.getValueAt(selectedRow, 2).toString();
        String memberNAME = tableDataOfListOfIssuedBooks.getValueAt(selectedRow, 3).toString();
        double penalty = Double.parseDouble(tableDataOfListOfIssuedBooks.getValueAt(selectedRow, 7).toString());

        int numBorrowed = Integer.parseInt(tableDataOfListOfIssuedBooks.getValueAt(selectedRow, 8).toString());

        // Prompt the user to enter the quantity of books to return
        int quantityToReturn = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the quantity of books to return:", "1"));

        // Display an error message if the quantity is invalid
        if (quantityToReturn <= 0 || quantityToReturn > numBorrowed) {
            JOptionPane.showMessageDialog(null, "Invalid quantity.");
            return;
        }

        // Update the book quantity in the database
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE book_details SET quantity = quantity + ? WHERE book_title = ?");
            updateStmt.setInt(1, quantityToReturn);
            updateStmt.setString(2, bookTitle);
            updateStmt.executeUpdate();

           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating book quantity in database.");
            return;
        }

        // Update the number of books borrowed in the JTable
        int updatedNumBorrowed = numBorrowed - quantityToReturn;
        tableDataOfListOfIssuedBooks.setValueAt(updatedNumBorrowed, selectedRow, 8);

        // Remove the row from the JTable and delete the record from the database if necessary
        if (updatedNumBorrowed <= 0) {
            DefaultTableModel model = (DefaultTableModel) tableDataOfListOfIssuedBooks.getModel();
            model.removeRow(selectedRow);
            try {
                PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM issued_books WHERE book_title = ? AND member_id = ?");
                deleteStmt.setString(1, bookTitle);
                deleteStmt.setString(2, memberID);
                deleteStmt.executeUpdate();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error deleting record from database.");
                return;
            }
        }

        // Insert a record in the "book_returned" table of the database
        try {
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO book_returned (book_title, member_id,Member_name, quantity_returned, returned_date, Status) VALUES (?, ?, ?,?, ?, ?)");
            insertStmt.setString(1, bookTitle);
            insertStmt.setString(2, memberID);
            insertStmt.setString(3, memberNAME);
            insertStmt.setInt(4, quantityToReturn);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String returnedDate = dateFormat.format(new java.sql.Date(System.currentTimeMillis()));
            insertStmt.setString(5, returnedDate);
            insertStmt.setString(6, "Returned");
            insertStmt.executeUpdate();

            // Show a success message
            JOptionPane.showMessageDialog(null, "Book returned successfully.");

            // Update the returned books table
            showReturnedBooksDataToTable();
            showBookDataToTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error inserting record into database.");
            return;
        }
    }//GEN-LAST:event_buttonforReturnBookInListOfIssuedBooksActionPerformed

    private void txtSearchMemberIdMemberNameBookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMemberIdMemberNameBookTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMemberIdMemberNameBookTitleActionPerformed

    private void txtSearchMemberIdMemberNameBookTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMemberIdMemberNameBookTitleKeyReleased
        String searchmemberidMemberNameBookTitle = txtSearchMemberIdMemberNameBookTitle.getText();
        searchcrud_tableDataOfMemberIDNameBooktitle(searchmemberidMemberNameBookTitle);
    }//GEN-LAST:event_txtSearchMemberIdMemberNameBookTitleKeyReleased

    private int idN = 1;
    private void crud_updateBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_updateBtn2ActionPerformed
        // When the Update button is clicked, prepare the SQL statement with the new data
        String updateData = "UPDATE book_details SET book_title=?, author=?, publisher=?, category=?, quantity=?, status=? WHERE book_id=?";
        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields are filled
            if (txt_booktitle.getText().isEmpty() || txt_author.getText().isEmpty() || txt_publisher.getText().isEmpty() || txt_category.getText().isEmpty() || txt_quantity.getText().isEmpty() || jComboBox_statusBook.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a row first", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if quantity is a number
                String quantityStr = txt_quantity.getText().trim();
                if (!quantityStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Confirm with user if they want to update data
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to UPDATE? ", "Confirmation Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        pst = con.prepareStatement(updateData);
                        pst.setString(1, txt_booktitle.getText());
                        pst.setString(2, txt_author.getText());
                        pst.setString(3, txt_publisher.getText());
                        pst.setString(4, txt_category.getText());
                        pst.setInt(5, Integer.parseInt(txt_quantity.getText()));
                        pst.setString(6, jComboBox_statusBook.getSelectedItem().toString());
                        pst.setInt(7, idN);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                        //TO SHOW UPDATED DATA
                        showBookDataToTable();
                        displaynoOfUnAvailableBooks();
                        displaynoOfAvailableBooks();
                        displayTotalOfBooks();

                        //CLEAR FIELDS
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cancelled update!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_crud_updateBtn2ActionPerformed

    private void jComboBox_gradelevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_gradelevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_gradelevelActionPerformed

    private void jComboBox_strandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_strandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_strandActionPerformed

    private void crud_tableDataOfReturnedBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfReturnedBooksMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_crud_tableDataOfReturnedBooksMouseClicked

    private void txtSearchBookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBookTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBookTitleActionPerformed

    private void crud_deleteBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_deleteBtn1ActionPerformed
      
    }//GEN-LAST:event_crud_deleteBtn1ActionPerformed
    private void searchcrud_tableDataOfMemberIDNameBooktitle(String SearchmemberNidBooktitle) {
        // Initialize the table model if it is not already initialized
        if (model == null) {
            model = (DefaultTableModel) tableDataOfListOfIssuedBooks.getModel();
        }

        // Create a TableRowSorter and set it to the table
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tableDataOfListOfIssuedBooks.setRowSorter(trs);

        // Set the row filter using the searchMemberId
        trs.setRowFilter(RowFilter.regexFilter(SearchmemberNidBooktitle, 1, 3, 4));
    }

    private void searchcrud_tableDataOfBooks(String searchBookTitle) {
        // Initialize the table model if it is not already initialized
        if (model == null) {
            model = (DefaultTableModel) crud_tableDataOfBooks.getModel();
        }

        // Create a TableRowSorter and set it to the table
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        crud_tableDataOfBooks.setRowSorter(trs);

        // Set the row filter using the searchMemberId
        trs.setRowFilter(RowFilter.regexFilter(searchBookTitle, 1));
    }

    private void searchcrud_tableDataOfmembers(String searchmemberidmaint) {
        // Initialize the table model if it is not already initialized
        if (model == null) {
            model = (DefaultTableModel) crud_tableDataOfmembers.getModel();
        }

        // Create a TableRowSorter and set it to the table
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        crud_tableDataOfmembers.setRowSorter(trs);

        // Set the row filter using the searchMemberId
        trs.setRowFilter(RowFilter.regexFilter(searchmemberidmaint, 0));
    }

    public void clearFields() {
        txt_booktitle.setText("");
        txt_author.setText("");
        txt_publisher.setText("");
        txt_category.setText("");
        txt_quantity.setText("");
        jComboBox_statusBook.setSelectedItem(null);

    }

    public void clearFieldsForMember() {
        txt_Memberfirstname.setText("");
        txt_Membermiddlename.setText("");
        txt_Memberlastname.setText("");
        txtsuffix.setText("");
        txt_Membersection.setText("");
        txt_Membercontact.setText("");
        formattedtxt_schoolyear.setText("");
        jComboBox_Membergender.setSelectedItem(null);
        jComboBox_membertype.setSelectedItem(null);
        jComboBox_gradelevel.setSelectedItem(null);
        jComboBox_strand.setSelectedItem(null);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BookAuthorLabelForBookDetails;
    private javax.swing.JLabel BookCategoryLabelForBookDetails;
    private javax.swing.JLabel BookQuantitylabelForBookDetails;
    private javax.swing.JLabel LibrarianNameDisplay;
    private javax.swing.JLabel MemberNameLabelForStudentDetails;
    private javax.swing.JLabel MemberSectionLabelForStudentDetails;
    private javax.swing.JLabel MemberStrandLabelForStudentDetails;
    private javax.swing.JLabel MemberTypeLabelForStudentDetails;
    private javax.swing.JPanel Parent;
    private javax.swing.JButton SearchButtonForBookId;
    private javax.swing.JButton SearchButtonForMemberId;
    private javax.swing.JPanel bookMaintenanceCardLayout;
    private javax.swing.JPanel bookTransactionCardLayout;
    private rojerusan.RSMaterialButtonRectangle buttonforIssueBookOfBookTransaction;
    private rojerusan.RSMaterialButtonRectangle buttonforReturnBookInListOfIssuedBooks;
    private javax.swing.JLabel contact_label;
    private rojerusan.RSMaterialButtonRectangle crud_MemberAdd;
    private rojerusan.RSMaterialButtonRectangle crud_MemberclearBtn;
    private rojerusan.RSMaterialButtonRectangle crud_MemberdeleteBtn;
    private rojerusan.RSMaterialButtonRectangle crud_MemberupdateBtn;
    private rojerusan.RSMaterialButtonRectangle crud_addBtn;
    private rojerusan.RSMaterialButtonRectangle crud_clearBtn;
    private rojerusan.RSMaterialButtonRectangle crud_deleteBtn;
    private rojerusan.RSMaterialButtonRectangle crud_deleteBtn1;
    private rojerusan.RSTableMetro crud_tableDataOfBooks;
    private rojerusan.RSTableMetro crud_tableDataOfReturnedBooks;
    private rojerusan.RSTableMetro crud_tableDataOfmembers;
    private rojerusan.RSMaterialButtonRectangle crud_updateBtn2;
    private javax.swing.JPanel dashboardCardLayout;
    private javax.swing.JLabel firstname_label;
    private javax.swing.JFormattedTextField formattedtxt_schoolyear;
    private javax.swing.JLabel gender_label2;
    private javax.swing.JLabel gradelevel_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_Membergender;
    private javax.swing.JComboBox<String> jComboBox_gradelevel;
    private javax.swing.JComboBox<String> jComboBox_membertype;
    private javax.swing.JComboBox<String> jComboBox_statusBook;
    private javax.swing.JComboBox<String> jComboBox_strand;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelsearchbooktitle;
    private javax.swing.JLabel labelsearchmemberid;
    private javax.swing.JLabel lastname_label;
    private javax.swing.JLabel lastname_label1;
    private javax.swing.JPanel listOfIssuedBooksCardLayout;
    private javax.swing.JPanel memberMaintenanceCardLayout;
    private javax.swing.JLabel membertype_label;
    private javax.swing.JLabel middlename_label;
    private rojerusan.RSMetroTextPlaceHolderBeanInfo rSMetroTextPlaceHolderBeanInfo1;
    private javax.swing.JLabel schoolyear_label;
    private javax.swing.JLabel schoolyear_label1;
    private javax.swing.JLabel sectionstrand_label;
    private rojerusan.RSTableMetro tableDataOfListOfIssuedBooks;
    private javax.swing.JLabel txtNoofBorrowers;
    private javax.swing.JTextField txtSearchBookTitle;
    private javax.swing.JTextField txtSearchMemberIdMemberNameBookTitle;
    private javax.swing.JTextField txtSearchmemberidForMemberMaint;
    private javax.swing.JLabel txtTotalreturnedbooks;
    private javax.swing.JTextField txt_BookTitle_BookTransac;
    private javax.swing.JTextField txt_BooksToBeBorrowed;
    private javax.swing.JTextField txt_MaxDaysAllowed;
    private javax.swing.JTextField txt_MemberId_BookTransac;
    private javax.swing.JTextField txt_Membercontact;
    private javax.swing.JTextField txt_Memberfirstname;
    private javax.swing.JTextField txt_Memberlastname;
    private javax.swing.JTextField txt_Membermiddlename;
    private javax.swing.JTextField txt_Membersection;
    private javax.swing.JTextField txt_Penalty;
    private javax.swing.JTextField txt_author;
    private javax.swing.JTextField txt_booktitle;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextField txt_publisher;
    private javax.swing.JTextField txt_quantity;
    private javax.swing.JLabel txtnoOfPenalties;
    private javax.swing.JLabel txtnoOfissuedBooks;
    private javax.swing.JLabel txtnoofavailablebooks;
    private javax.swing.JPanel txtnoofborrowers;
    private javax.swing.JLabel txtnoofunavailablebooks;
    private javax.swing.JTextField txtsuffix;
    private javax.swing.JLabel txttotalbooks;
    // End of variables declaration//GEN-END:variables
}
