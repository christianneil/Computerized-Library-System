package library.management.newn;

import java.sql.SQLException;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LibrarianDashboard extends javax.swing.JFrame {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    Color mouseExitColor = new Color(144, 0, 0);
    Color mouseClickColor = new Color(96, 0, 0);
    DefaultTableModel model;

    /*private Color hoverBgColor = new Color(255, 255, 255); 
    private Color hoverTextColor = new Color(144, 0, 63); 
    private Color pressedBgColor = new Color(199, 0, 57); 
    private Color releasedBgColor = new Color(144, 0, 63); */
    public LibrarianDashboard() {
        initComponents();
        showBookDataToTable();
        showMemberDataToTable();
    }

    // This method fetches book data from the database table and displays it in a JTable
    public void showBookDataToTable() {

        // Declare and initialize variables to store book data
        String BookId = null;
        String BookTitle = null;
        String Author = null;
        String Publisher = null;
        String Category = null;
        String Quantity = null;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/compulibsys", "root", "");
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
                Quantity = rs.getString("quantity");
                Status = rs.getString("status");

                // Create a string array with the retrieved book data
                String rows[] = {BookId, BookTitle, Author, Publisher, Category, Quantity, Status};
                // Add the book data to the JTable model
                model.addRow(rows);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showMemberDataToTable() {

        // Declare and initialize variables to store member data
        String Id = null;
        String FirstName = null;
        String MiddleName = null;
        String LastName = null;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/compulibsys", "root", "");
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
                Section = rs.getString("section");
                SchoolYear = rs.getString("school_year");
                Contact = rs.getString("contact");
                Gender = rs.getString("gender");
                MemberType = rs.getString("member_type");
                GradeLevel = rs.getString("grade_level");
                Strand = rs.getString("strand");

                // Create a string array with the retrieved book data
                String rows[] = {Id, FirstName, MiddleName, LastName, Section, SchoolYear, Contact, Gender, MemberType, GradeLevel, Strand};
                // Add the book data to the JTable model
                model.addRow(rows);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * public void setBookToTable() {
     *
     *
     * DefaultTableModel model = (DefaultTableModel)
     * crud_tableDataOfBooks.getModel(); model.setRowCount(0);
     *
     * try { Class.forName("com.mysql.cj.jdbc.Driver"); con =
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/compulibsys",
     * "root", ""); pst = con.prepareStatement(selectData); rs =
     * pst.executeQuery(); } catch (Exception e) { }
     *
     * }
     */
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
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        listOfIssuedBooksCardLayout = new javax.swing.JPanel();
        bookTransactionCardLayout = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txt_booktitle6 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_booktitle7 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txt_booktitle5 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_booktitle8 = new javax.swing.JTextField();
        txt_booktitle9 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        txt_booktitle1 = new javax.swing.JTextField();
        txt_booktitle2 = new javax.swing.JTextField();
        txt_booktitle3 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txt_booktitle4 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txt_booktitle10 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        crud_tableDataOfBooks1 = new rojerusan.RSTableMetro();
        jLabel45 = new javax.swing.JLabel();
        txt_booktitle11 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        memberMaintenanceCardLayout = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        crud_tableDataOfmembers = new rojerusan.RSTableMetro();
        firstname_label = new javax.swing.JLabel();
        txt_Memberfirstname = new javax.swing.JTextField();
        middlename_label = new javax.swing.JLabel();
        txt_Membermiddlename = new javax.swing.JTextField();
        lastname_label = new javax.swing.JLabel();
        txt_Memberlastname = new javax.swing.JTextField();
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
        bookMaintenanceCardLayout = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_author = new javax.swing.JTextField();
        txt_booktitle = new javax.swing.JTextField();
        txt_category = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox_statusBook = new javax.swing.JComboBox<>();
        crud_addBtn = new rojerusan.RSMaterialButtonRectangle();
        crud_updateBtn = new rojerusan.RSMaterialButtonRectangle();
        crud_deleteBtn = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        crud_tableDataOfBooks = new rojerusan.RSTableMetro();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        txt_publisher = new javax.swing.JTextField();
        crud_clearBtn = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(144, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(144, 0, 0));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-books-24.png"))); // NOI18N
        jLabel17.setText(" List of Issued Books");
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
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 250, 40));

        jPanel13.setBackground(new java.awt.Color(144, 0, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/management/newn/iconsforLibrarianSideMenu/icons8-transaction-24.png"))); // NOI18N
        jLabel1.setText(" Book Transaction ");
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

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 250, 40));

        jPanel16.setBackground(new java.awt.Color(144, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 250, 50));

        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 110, 40));

        jPanel5.setBackground(new java.awt.Color(144, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 240, -1));

        jPanel6.setBackground(new java.awt.Color(144, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 250, 40));

        jSeparator1.setForeground(new java.awt.Color(241, 184, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 140, 10));

        jPanel7.setBackground(new java.awt.Color(144, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 250, 40));

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

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Welcome :");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 90, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 0, 1120, 50));

        Parent.setBackground(new java.awt.Color(255, 255, 255));
        Parent.setLayout(new java.awt.CardLayout());

        dashboardCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        dashboardCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setText("Total Books");
        dashboardCardLayout.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 220, 30));

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel10.setPreferredSize(new java.awt.Dimension(260, 1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("5");

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardCardLayout.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 260, 140));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Listed Categories");
        dashboardCardLayout.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 220, 30));

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel11.setPreferredSize(new java.awt.Dimension(260, 1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("10");

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
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardCardLayout.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 260, 140));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Dashboard");
        dashboardCardLayout.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel24.setText("No. of Borrowers");
        dashboardCardLayout.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 300, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel25.setText("No. of Available Books");
        dashboardCardLayout.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 310, 30));

        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel14.setPreferredSize(new java.awt.Dimension(260, 1));

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("5");

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
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardCardLayout.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, 140));

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel15.setPreferredSize(new java.awt.Dimension(260, 1));

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("5");

        jLabel29.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("5");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardCardLayout.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, -1, 140));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel30.setText("No. of Issued Books");
        dashboardCardLayout.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 220, 30));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(241, 184, 20)));
        jPanel17.setPreferredSize(new java.awt.Dimension(260, 1));

        jLabel31.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("5");

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
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardCardLayout.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, 140));

        Parent.add(dashboardCardLayout, "card2");

        listOfIssuedBooksCardLayout.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout listOfIssuedBooksCardLayoutLayout = new javax.swing.GroupLayout(listOfIssuedBooksCardLayout);
        listOfIssuedBooksCardLayout.setLayout(listOfIssuedBooksCardLayoutLayout);
        listOfIssuedBooksCardLayoutLayout.setHorizontalGroup(
            listOfIssuedBooksCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        listOfIssuedBooksCardLayoutLayout.setVerticalGroup(
            listOfIssuedBooksCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        Parent.add(listOfIssuedBooksCardLayout, "card3");

        bookTransactionCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_booktitle6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle6.setSelectionStart(2);
        txt_booktitle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle6ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_booktitle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 210, 30));

        jLabel40.setBackground(new java.awt.Color(241, 184, 20));
        jLabel40.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 31));

        txt_booktitle7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle7.setSelectionStart(2);
        txt_booktitle7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle7ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_booktitle7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 210, 30));

        jLabel38.setBackground(new java.awt.Color(241, 184, 20));
        jLabel38.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel38.setText("Section");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, 31));

        txt_booktitle5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle5.setSelectionStart(2);
        txt_booktitle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle5ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_booktitle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 210, 30));

        jLabel39.setBackground(new java.awt.Color(241, 184, 20));
        jLabel39.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel39.setText("Member Name");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 31));

        jLabel41.setBackground(new java.awt.Color(241, 184, 20));
        jLabel41.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel41.setText("Member ID");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 31));

        jLabel42.setBackground(new java.awt.Color(241, 184, 20));
        jLabel42.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel42.setText("Member Type");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 31));

        jLabel43.setBackground(new java.awt.Color(241, 184, 20));
        jLabel43.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel43.setText("Strand");
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 31));

        txt_booktitle8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle8.setSelectionStart(2);
        txt_booktitle8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle8ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_booktitle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 210, 30));

        txt_booktitle9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle9.setSelectionStart(2);
        txt_booktitle9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle9ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_booktitle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 210, 30));

        bookTransactionCardLayout.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 370, 330));

        jPanel9.setBackground(new java.awt.Color(51, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_booktitle1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle1.setSelectionStart(2);
        txt_booktitle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle1ActionPerformed(evt);
            }
        });
        jPanel9.add(txt_booktitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 210, 30));

        txt_booktitle2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle2.setSelectionStart(2);
        txt_booktitle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle2ActionPerformed(evt);
            }
        });
        jPanel9.add(txt_booktitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 210, 30));

        txt_booktitle3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle3.setSelectionStart(2);
        txt_booktitle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle3ActionPerformed(evt);
            }
        });
        jPanel9.add(txt_booktitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 210, 30));

        jLabel34.setBackground(new java.awt.Color(241, 184, 20));
        jLabel34.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel34.setText("Book Title");
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 31));

        jLabel33.setBackground(new java.awt.Color(241, 184, 20));
        jLabel33.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel33.setText("Book ID");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 31));

        jLabel35.setBackground(new java.awt.Color(241, 184, 20));
        jLabel35.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel35.setText("Category");
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 31));

        bookTransactionCardLayout.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 22, 360, 330));

        jLabel36.setBackground(new java.awt.Color(241, 184, 20));
        jLabel36.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel36.setText("Book Title");
        bookTransactionCardLayout.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, -1, 31));

        txt_booktitle4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle4.setSelectionStart(2);
        txt_booktitle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle4ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(txt_booktitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 210, 30));

        jLabel13.setText("BOOK DETAILS");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );

        bookTransactionCardLayout.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 20));

        jLabel37.setText("STUDENT DETAILS");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(jLabel37))
        );

        bookTransactionCardLayout.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 380, -1));

        jLabel44.setBackground(new java.awt.Color(241, 184, 20));
        jLabel44.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel44.setText("Student Name");
        bookTransactionCardLayout.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, -1, 31));

        txt_booktitle10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle10.setSelectionStart(2);
        txt_booktitle10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle10ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(txt_booktitle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 210, 30));

        crud_tableDataOfBooks1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Title", "Category", "Mmeber Name", "Member ID", "Type", "Strand", "Section"
            }
        ));
        crud_tableDataOfBooks1.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfBooks1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfBooks1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(crud_tableDataOfBooks1);

        bookTransactionCardLayout.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 740, 350));

        jLabel45.setBackground(new java.awt.Color(241, 184, 20));
        jLabel45.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel45.setText("Student ID");
        bookTransactionCardLayout.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, -1, 31));

        txt_booktitle11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle11.setSelectionStart(2);
        txt_booktitle11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitle11ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(txt_booktitle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 450, 210, 30));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Returned Books");
        bookTransactionCardLayout.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, 130, 40));

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, 90, 40));

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, 90, 40));

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        bookTransactionCardLayout.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 520, 90, 40));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bookTransactionCardLayout.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, 370, -1));

        Parent.add(bookTransactionCardLayout, "card4");

        memberMaintenanceCardLayout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crud_tableDataOfmembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Member ID", "First Name", "Middle Name", "Last Name", "Section", "School Year", "Contact", "Gender", "Member Type", "Grade Level", "Strand"
            }
        ));
        crud_tableDataOfmembers.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfmembers.setFuenteFilas(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        crud_tableDataOfmembers.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        crud_tableDataOfmembers.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        crud_tableDataOfmembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfmembersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(crud_tableDataOfmembers);

        memberMaintenanceCardLayout.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1090, 430));

        firstname_label.setBackground(new java.awt.Color(241, 184, 20));
        firstname_label.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        firstname_label.setText("First Name");
        memberMaintenanceCardLayout.add(firstname_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 31));

        txt_Memberfirstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Memberfirstname.setSelectionStart(2);
        txt_Memberfirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemberfirstnameActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(txt_Memberfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, 20));

        middlename_label.setBackground(new java.awt.Color(241, 184, 20));
        middlename_label.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        middlename_label.setText("Middle Name");
        memberMaintenanceCardLayout.add(middlename_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 30));

        txt_Membermiddlename.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membermiddlename.setSelectionStart(2);
        txt_Membermiddlename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MembermiddlenameActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(txt_Membermiddlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 190, 20));

        lastname_label.setBackground(new java.awt.Color(241, 184, 20));
        lastname_label.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        lastname_label.setText("Last Name");
        memberMaintenanceCardLayout.add(lastname_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, 30));

        txt_Memberlastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Memberlastname.setSelectionStart(2);
        txt_Memberlastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemberlastnameActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(txt_Memberlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 190, 20));

        jComboBox_Membergender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jComboBox_Membergender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_MembergenderActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(jComboBox_Membergender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 180, -1));

        membertype_label.setBackground(new java.awt.Color(241, 184, 20));
        membertype_label.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        membertype_label.setText("Member Type");
        memberMaintenanceCardLayout.add(membertype_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, 31));

        jComboBox_strand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "           ", "ABM", "HUMSS", "ICT", "STEM" }));
        jComboBox_strand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_strandActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(jComboBox_strand, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 190, -1));

        gradelevel_label.setBackground(new java.awt.Color(241, 184, 20));
        gradelevel_label.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        gradelevel_label.setText("Grade Level");
        memberMaintenanceCardLayout.add(gradelevel_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, 30));

        sectionstrand_label.setBackground(new java.awt.Color(241, 184, 20));
        sectionstrand_label.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        sectionstrand_label.setText("Strand *");
        memberMaintenanceCardLayout.add(sectionstrand_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, -1, 30));

        contact_label.setBackground(new java.awt.Color(241, 184, 20));
        contact_label.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        contact_label.setText("Contact");
        memberMaintenanceCardLayout.add(contact_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 60, 30));

        txt_Membercontact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membercontact.setSelectionStart(2);
        txt_Membercontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MembercontactActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(txt_Membercontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 190, 20));

        schoolyear_label.setBackground(new java.awt.Color(241, 184, 20));
        schoolyear_label.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        schoolyear_label.setText("Section");
        memberMaintenanceCardLayout.add(schoolyear_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        txt_Membersection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Membersection.setSelectionStart(2);
        txt_Membersection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MembersectionActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(txt_Membersection, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 190, 20));

        schoolyear_label1.setBackground(new java.awt.Color(241, 184, 20));
        schoolyear_label1.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        schoolyear_label1.setText("School Year");
        memberMaintenanceCardLayout.add(schoolyear_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, 30));

        jComboBox_gradelevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11", "12" }));
        jComboBox_gradelevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_gradelevelActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(jComboBox_gradelevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 190, -1));

        jComboBox_membertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Faculty", "Staff" }));
        jComboBox_membertype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_membertypeActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(jComboBox_membertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 190, -1));

        gender_label2.setBackground(new java.awt.Color(241, 184, 20));
        gender_label2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        gender_label2.setText("Gender");
        memberMaintenanceCardLayout.add(gender_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, 30));

        try {
            formattedtxt_schoolyear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        memberMaintenanceCardLayout.add(formattedtxt_schoolyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 190, -1));

        crud_MemberAdd.setBackground(new java.awt.Color(0, 0, 144));
        crud_MemberAdd.setText("add");
        crud_MemberAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberAddActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(crud_MemberAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 180, 40));

        crud_MemberupdateBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_MemberupdateBtn.setText("UPDATE");
        crud_MemberupdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberupdateBtnActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(crud_MemberupdateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 180, 40));

        crud_MemberdeleteBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_MemberdeleteBtn.setText("DELETE");
        crud_MemberdeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberdeleteBtnActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(crud_MemberdeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 170, 40));

        crud_MemberclearBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_MemberclearBtn.setText("CLEAR");
        crud_MemberclearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_MemberclearBtnActionPerformed(evt);
            }
        });
        memberMaintenanceCardLayout.add(crud_MemberclearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 200, 40));

        Parent.add(memberMaintenanceCardLayout, "card5");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(241, 184, 20));
        jLabel9.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel9.setText("Status");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, 31));

        txt_author.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_author.setSelectionStart(2);
        txt_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorActionPerformed(evt);
            }
        });
        jPanel8.add(txt_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 330, 30));

        txt_booktitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_booktitle.setSelectionStart(2);
        txt_booktitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booktitleActionPerformed(evt);
            }
        });
        jPanel8.add(txt_booktitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 330, 30));

        txt_category.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_category.setSelectionStart(2);
        txt_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_categoryActionPerformed(evt);
            }
        });
        jPanel8.add(txt_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 330, 30));

        jLabel15.setBackground(new java.awt.Color(241, 184, 20));
        jLabel15.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel15.setText("Book Title");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 31));

        jLabel16.setBackground(new java.awt.Color(241, 184, 20));
        jLabel16.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel16.setText("Author");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        jLabel18.setBackground(new java.awt.Color(241, 184, 20));
        jLabel18.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel18.setText("Publisher");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jComboBox_statusBook.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jComboBox_statusBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_statusBookActionPerformed(evt);
            }
        });
        jPanel8.add(jComboBox_statusBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 310, 40));

        crud_addBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_addBtn.setText("add");
        crud_addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_addBtnActionPerformed(evt);
            }
        });
        jPanel8.add(crud_addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 310, 40));

        crud_updateBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_updateBtn.setText("UPDATE");
        crud_updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_updateBtnActionPerformed(evt);
            }
        });
        jPanel8.add(crud_updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 310, 40));

        crud_deleteBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_deleteBtn.setText("DELETE");
        crud_deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_deleteBtnActionPerformed(evt);
            }
        });
        jPanel8.add(crud_deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 310, 40));

        crud_tableDataOfBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Book Title", "Author", "Publisher", "Category", "Quantity", "Status"
            }
        ));
        crud_tableDataOfBooks.setColorBackgoundHead(new java.awt.Color(241, 184, 20));
        crud_tableDataOfBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crud_tableDataOfBooksMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(crud_tableDataOfBooks);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 630, 450));

        jLabel21.setBackground(new java.awt.Color(241, 184, 20));
        jLabel21.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel21.setText("Quantity");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jLabel23.setBackground(new java.awt.Color(241, 184, 20));
        jLabel23.setFont(new java.awt.Font("Nirmala UI", 0, 16)); // NOI18N
        jLabel23.setText("Category");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        txt_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_quantity.setSelectionStart(2);
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        jPanel8.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 330, 30));

        txt_publisher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_publisher.setSelectionStart(2);
        txt_publisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_publisherActionPerformed(evt);
            }
        });
        jPanel8.add(txt_publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 330, 30));

        crud_clearBtn.setBackground(new java.awt.Color(0, 0, 144));
        crud_clearBtn.setText("CLEAR");
        crud_clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crud_clearBtnActionPerformed(evt);
            }
        });
        jPanel8.add(crud_clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, 310, 40));

        javax.swing.GroupLayout bookMaintenanceCardLayoutLayout = new javax.swing.GroupLayout(bookMaintenanceCardLayout);
        bookMaintenanceCardLayout.setLayout(bookMaintenanceCardLayoutLayout);
        bookMaintenanceCardLayoutLayout.setHorizontalGroup(
            bookMaintenanceCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookMaintenanceCardLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        bookMaintenanceCardLayoutLayout.setVerticalGroup(
            bookMaintenanceCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookMaintenanceCardLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

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

    private void txt_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorActionPerformed

    private void txt_booktitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitleActionPerformed

    private void txt_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_categoryActionPerformed

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void jComboBox_statusBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_statusBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_statusBookActionPerformed

    private void txt_publisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_publisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_publisherActionPerformed

    private void crud_clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_clearBtnActionPerformed
        clearFields();
    }//GEN-LAST:event_crud_clearBtnActionPerformed

    private void crud_addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_addBtnActionPerformed
        // Display the current book data in the JTable
        showBookDataToTable();

        // SQL statement to get the maximum book ID from the book_details table
        String getMaxId = "SELECT MAX(book_id) FROM book_details";
        // SQL statement to insert book data into the book_details table
        String insertData = "INSERT INTO book_details (book_id, book_title, author, publisher, category, quantity, status) VALUES (?,?,?,?,?,?,?)";

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
                // Get the maximum book ID from the database and increment it by 1 to generate a new, unique ID for the book being added
                pst = con.prepareStatement(getMaxId);
                ResultSet rs = pst.executeQuery();
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
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                // Update the JTable to show the newly added book data
                showBookDataToTable();

                // CLEAR FIELDS
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_crud_addBtnActionPerformed

    private void crud_updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_updateBtnActionPerformed
        // When the Update button is clicked, prepare the SQL statement with the new data
        String updateData = "UPDATE book_details SET book_title='"
                + txt_booktitle.getText() + "', author='"
                + txt_author.getText() + "', publisher='"
                + txt_publisher.getText() + "', category='"
                + txt_category.getText() + "', quantity='"
                + txt_quantity.getText() + "', status='"
                + jComboBox_statusBook.getSelectedItem() + "' WHERE book_id=" + idN;

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
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                        //TO SHOW UPDATED DATA
                        showBookDataToTable();

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
    }//GEN-LAST:event_crud_updateBtnActionPerformed

    private int idN = 0;// Initialize a variable to store the ID of the selected book
    private void crud_tableDataOfBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfBooksMouseClicked
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfBooks.getModel();
        int index = crud_tableDataOfBooks.getSelectedRow();

        // Get the ID of the selected book
        idN = Integer.valueOf(model.getValueAt(index, 0).toString());

        txt_booktitle.setText(model.getValueAt(index, 1).toString());
        txt_author.setText(model.getValueAt(index, 2).toString());
        txt_publisher.setText(model.getValueAt(index, 3).toString());
        txt_category.setText(model.getValueAt(index, 4).toString());
        txt_quantity.setText(model.getValueAt(index, 5).toString());
        jComboBox_statusBook.setSelectedItem(model.getValueAt(index, 6).toString());
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
                    showBookDataToTable();

                    //CLEAR FIELDS
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cancelled deletion!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // This block of code is executed regardless of whether an exception was thrown or not.
            // It is used to ensure that database resources (in this case, the PreparedStatement and Connection objects) are properly closed.
            try {
                // Check if the PreparedStatement object is not null
                if (pst != null) {
                    // If it's not null, close it to release any database resources it may be holding
                    pst.close();
                }
                // Check if the Connection object is not null
                if (con != null) {
                    // If it's not null, close it to release any database resources it may be holding
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_crud_deleteBtnActionPerformed
   
    private void crud_tableDataOfmembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfmembersMouseClicked
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfmembers.getModel();
        int index = crud_tableDataOfmembers.getSelectedRow();

        // Get the ID of the selected book
        idN = Integer.valueOf(model.getValueAt(index, 0).toString());

        txt_Memberfirstname.setText(model.getValueAt(index, 1).toString());
        txt_Membermiddlename.setText(model.getValueAt(index, 2).toString());
        txt_Memberlastname.setText(model.getValueAt(index, 3).toString());
        txt_Membersection.setText(model.getValueAt(index, 4).toString());
        formattedtxt_schoolyear.setText(model.getValueAt(index, 5).toString());
        txt_Membercontact.setText(model.getValueAt(index, 6).toString());
        jComboBox_Membergender.setSelectedItem(model.getValueAt(index, 7).toString());
        jComboBox_membertype.setSelectedItem(model.getValueAt(index, 8).toString());
        jComboBox_gradelevel.setSelectedItem(model.getValueAt(index, 9).toString());
        jComboBox_strand.setSelectedItem(model.getValueAt(index, 10).toString());
    }//GEN-LAST:event_crud_tableDataOfmembersMouseClicked

    private void txt_MemberfirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemberfirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemberfirstnameActionPerformed

    private void txt_MembermiddlenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MembermiddlenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MembermiddlenameActionPerformed

    private void txt_MemberlastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemberlastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemberlastnameActionPerformed

    private void jComboBox_MembergenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_MembergenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_MembergenderActionPerformed

    private void jComboBox_strandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_strandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_strandActionPerformed

    private void txt_MembercontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MembercontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MembercontactActionPerformed

    private void txt_MembersectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MembersectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MembersectionActionPerformed

    private void jComboBox_gradelevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_gradelevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_gradelevelActionPerformed

    private void jComboBox_membertypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_membertypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_membertypeActionPerformed

    private void crud_MemberAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberAddActionPerformed
        // Display the current book data in the JTable
        showMemberDataToTable();

        // SQL statement to get the maximum book ID from the book_daetails table
        String getMaxId = "SELECT MAX(member_id) FROM memberinfo_table";
        // SQL statement to insert book data into the book_details table
        String insertData = "INSERT INTO memberinfo_table (member_id,first_name,middle_name,last_name,section,school_year,contact,gender,member_type,grade_level,strand) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        // Get a connection to the database
        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields have been filled out, otherwise show an error message
            if (txt_Memberfirstname.getText().isEmpty() || txt_Membermiddlename.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || txt_Membersection.getText().isEmpty() || formattedtxt_schoolyear.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
                if (!txt_Memberfirstname.hasFocus() || !txt_Membermiddlename.hasFocus() || !txt_Memberlastname.hasFocus() || !txt_Membersection.hasFocus() || !formattedtxt_schoolyear.hasFocus() || !jComboBox_Membergender.hasFocus() || !jComboBox_membertype.hasFocus() || !jComboBox_gradelevel.hasFocus() || !jComboBox_strand.hasFocus()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all necessary information", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
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
                // Get the maximum book ID from the database and increment it by 1 to generate a new, unique ID for the book being added
                pst = con.prepareStatement(getMaxId);
                ResultSet rs = pst.executeQuery();
                int maxId = rs.next() ? rs.getInt(1) : 0; // If there are no existing book IDs, use the default value of 0 as the maximum
                int newId = maxId + 1;

                // Insert the book data into the book_details table
                pst = con.prepareStatement(insertData);
                pst.setInt(1, newId);

                pst.setString(2, txt_Memberfirstname.getText());
                pst.setString(3, txt_Membermiddlename.getText());
                pst.setString(4, txt_Memberlastname.getText());
                pst.setString(5, txt_Membersection.getText());
                pst.setString(6, formattedtxt_schoolyear.getText());
                pst.setString(7, txt_Membercontact.getText());
                pst.setString(8, (String) jComboBox_Membergender.getSelectedItem());
                pst.setString(9, (String) jComboBox_membertype.getSelectedItem());
                pst.setString(10, (String) jComboBox_gradelevel.getSelectedItem());
                pst.setString(11, (String) jComboBox_strand.getSelectedItem());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                // Update the JTable to show the newly added book data
                showMemberDataToTable();

                // CLEAR FIELDS
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_crud_MemberAddActionPerformed

    private void crud_MemberupdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberupdateBtnActionPerformed
        // When the Update button is clicked, prepare the SQL statement with the new data
        String updateData = "UPDATE memberinfo_table SET first_name='"
                + txt_Memberfirstname.getText() + "', middle_name='"
                + txt_Membermiddlename.getText() + "', last_name='"
                + txt_Memberlastname.getText() + "', section='"
                + txt_Membersection.getText() + "', school_year='"
                + formattedtxt_schoolyear.getText() + "', contact='"
                + txt_Membercontact.getText() + "', gender='"
                + jComboBox_Membergender.getSelectedIndex()+ "' member_type="
                + jComboBox_membertype.getSelectedItem() + "' grade_level="
                + jComboBox_gradelevel.getSelectedItem() + "' strand="
                + jComboBox_strand.getSelectedItem() + "' WHERE member_id=" + idN;
                

        con = Connectionz.getConnection();

        try {
            // Check if all necessary fields are filled
            if (txt_Memberfirstname.getText().isEmpty() || txt_Membermiddlename.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || txt_Membersection.getText().isEmpty() || formattedtxt_schoolyear.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a row first", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if contact is a number
                String txt_Membercontact = txt_Membercontact.getText().trim();
                if (!txt_Membercontact.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Confirm with user if they want to update data
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to UPDATE? ", "Confirmation Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        pst = con.prepareStatement(updateData);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Successfully Added", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                        //TO SHOW UPDATED DATA
                        showBookDataToTable();

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
    }//GEN-LAST:event_crud_MemberupdateBtnActionPerformed

    private void crud_MemberdeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberdeleteBtnActionPerformed
        // SQL command to delete the book data with the corresponding id number
        String deleteData = "DELETE FROM memberinfo_table WHERE member_id=" + String.valueOf(idN);
        // SQL command to update the book id numbers for the remaining records
        String updateIds = "UPDATE memberinfo_table SET member_id = member_id - 1 WHERE member_id > ?";

        try {
            if(txt_Memberfirstname.getText().isEmpty() || txt_Membermiddlename.getText().isEmpty() || txt_Memberlastname.getText().isEmpty() || txt_Membersection.getText().isEmpty() || formattedtxt_schoolyear.getText().isEmpty() || jComboBox_Membergender.getSelectedItem() == null || jComboBox_membertype.getSelectedItem() == null || jComboBox_gradelevel.getSelectedItem() == null || jComboBox_strand.getSelectedItem() == null) {
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
                    showBookDataToTable();

                    //CLEAR FIELDS
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cancelled deletion!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // This block of code is executed regardless of whether an exception was thrown or not.
            // It is used to ensure that database resources (in this case, the PreparedStatement and Connection objects) are properly closed.
            try {
                // Check if the PreparedStatement object is not null
                if (pst != null) {
                    // If it's not null, close it to release any database resources it may be holding
                    pst.close();
                }
                // Check if the Connection object is not null
                if (con != null) {
                    // If it's not null, close it to release any database resources it may be holding
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_crud_MemberdeleteBtnActionPerformed

    private void crud_MemberclearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crud_MemberclearBtnActionPerformed
        clearFieldsForMember();
    }//GEN-LAST:event_crud_MemberclearBtnActionPerformed

    private void txt_booktitle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle1ActionPerformed

    private void txt_booktitle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle2ActionPerformed

    private void txt_booktitle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle3ActionPerformed

    private void txt_booktitle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle4ActionPerformed

    private void txt_booktitle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle5ActionPerformed

    private void txt_booktitle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle6ActionPerformed

    private void txt_booktitle7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle7ActionPerformed

    private void txt_booktitle8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle8ActionPerformed

    private void txt_booktitle9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle9ActionPerformed

    private void txt_booktitle10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle10ActionPerformed

    private void crud_tableDataOfBooks1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crud_tableDataOfBooks1MouseClicked
        DefaultTableModel model = (DefaultTableModel) crud_tableDataOfBooks.getModel();
        int index = crud_tableDataOfBooks.getSelectedRow();

        // Get the ID of the selected book
        idN = Integer.valueOf(model.getValueAt(index, 0).toString());

        txt_booktitle.setText(model.getValueAt(index, 1).toString());
        txt_author.setText(model.getValueAt(index, 2).toString());
        txt_publisher.setText(model.getValueAt(index, 3).toString());
        txt_category.setText(model.getValueAt(index, 4).toString());
        txt_quantity.setText(model.getValueAt(index, 5).toString());
        jComboBox_statusBook.setSelectedItem(model.getValueAt(index, 6).toString());
    }//GEN-LAST:event_crud_tableDataOfBooks1MouseClicked

    private void txt_booktitle11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booktitle11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booktitle11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibrarianDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibrarianDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibrarianDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibrarianDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibrarianDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LibrarianNameDisplay;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel bookMaintenanceCardLayout;
    private javax.swing.JPanel bookTransactionCardLayout;
    private javax.swing.JLabel contact_label;
    private rojerusan.RSMaterialButtonRectangle crud_MemberAdd;
    private rojerusan.RSMaterialButtonRectangle crud_MemberclearBtn;
    private rojerusan.RSMaterialButtonRectangle crud_MemberdeleteBtn;
    private rojerusan.RSMaterialButtonRectangle crud_MemberupdateBtn;
    private rojerusan.RSMaterialButtonRectangle crud_addBtn;
    private rojerusan.RSMaterialButtonRectangle crud_clearBtn;
    private rojerusan.RSMaterialButtonRectangle crud_deleteBtn;
    private rojerusan.RSTableMetro crud_tableDataOfBooks;
    private rojerusan.RSTableMetro crud_tableDataOfBooks1;
    private rojerusan.RSTableMetro crud_tableDataOfmembers;
    private rojerusan.RSMaterialButtonRectangle crud_updateBtn;
    private javax.swing.JPanel dashboardCardLayout;
    private javax.swing.JLabel firstname_label;
    private javax.swing.JFormattedTextField formattedtxt_schoolyear;
    private javax.swing.JLabel gender_label2;
    private javax.swing.JLabel gradelevel_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox_Membergender;
    private javax.swing.JComboBox<String> jComboBox_gradelevel;
    private javax.swing.JComboBox<String> jComboBox_membertype;
    private javax.swing.JComboBox<String> jComboBox_statusBook;
    private javax.swing.JComboBox<String> jComboBox_strand;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lastname_label;
    private javax.swing.JPanel listOfIssuedBooksCardLayout;
    private javax.swing.JPanel memberMaintenanceCardLayout;
    private javax.swing.JLabel membertype_label;
    private javax.swing.JLabel middlename_label;
    private rojerusan.RSMetroTextPlaceHolderBeanInfo rSMetroTextPlaceHolderBeanInfo1;
    private javax.swing.JLabel schoolyear_label;
    private javax.swing.JLabel schoolyear_label1;
    private javax.swing.JLabel sectionstrand_label;
    private javax.swing.JTextField txt_Membercontact;
    private javax.swing.JTextField txt_Memberfirstname;
    private javax.swing.JTextField txt_Memberlastname;
    private javax.swing.JTextField txt_Membermiddlename;
    private javax.swing.JTextField txt_Membersection;
    private javax.swing.JTextField txt_author;
    private javax.swing.JTextField txt_booktitle;
    private javax.swing.JTextField txt_booktitle1;
    private javax.swing.JTextField txt_booktitle10;
    private javax.swing.JTextField txt_booktitle11;
    private javax.swing.JTextField txt_booktitle2;
    private javax.swing.JTextField txt_booktitle3;
    private javax.swing.JTextField txt_booktitle4;
    private javax.swing.JTextField txt_booktitle5;
    private javax.swing.JTextField txt_booktitle6;
    private javax.swing.JTextField txt_booktitle7;
    private javax.swing.JTextField txt_booktitle8;
    private javax.swing.JTextField txt_booktitle9;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextField txt_publisher;
    private javax.swing.JTextField txt_quantity;
    // End of variables declaration//GEN-END:variables
}
