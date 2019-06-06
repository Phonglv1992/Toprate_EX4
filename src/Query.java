import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Query {

    public static Scanner in = new Scanner(System.in);

    public void Options(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("MỜI BẠN CHỌN CHỨC NĂNG: ");
            System.out.println("1. Tìm kiếm sinh viên theo họ tên ");
            System.out.println("2. Thêm  một học viên cụ thể ");
            System.out.println("3. Sửa một học viên cụ thể ");
            System.out.println("4. Xóa một học viên cụ thể ");
            System.out.println("5. Thoát chương trình ");
            int chon=in.nextInt();
            switch (chon){
                case 1:
                    System.out.println("Nhập họ tên học viên cần tìm:");
                    String ht=input.nextLine();
                    SearchSVByHoTen(ht);
                    break;
                case 2:
                    addNewSV();
                    break;
                case 3:
                    System.out.println("id học viên cần sửa");
                    int id=input.nextInt();
                    updateSV(id);
                    break;
                case 4:
                    System.out.println("id sinh viên cần xóa: ");
                    int Id=input.nextInt();
                    deleteSV(Id);
                    break;
                case 5:
                    System.exit(0);
            }
        }

    }

    public boolean checkLogin(String account) {
        Connect connect = new Connect();
        try {
            Connection connection = connect.getConnect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from GV where ACCOUNT='" + account + "'");
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void logIn() {
        Scanner input=new Scanner(System.in);
        System.out.print("account: ");
        String account = input.nextLine();
        System.out.print("first-name: ");
        String first_Name = input.nextLine();
        System.out.print("last-name: ");
        String last_Name = input.nextLine();
        System.out.print("email: ");
        String email = input.nextLine();
        if (checkLogin(account)) {
            Connect con = new Connect();
            Connection connection = con.getConnect();
            List<SV> lstSV = new ArrayList<SV>();
            try {
                Statement statement = connection.createStatement();
                //String updateGV="update GV set GV.FIRST_NAME='"+first_Name+"',GV.LAST_NAME='"+last_Name+"',GV.EMAIL='"+email+"',GV.LAST_LOGIN = SYSDATE";
                statement.executeUpdate("update GV set GV.FIRST_NAME='" + first_Name + "',GV.LAST_NAME='" +
                        last_Name + "',GV.EMAIL='" + email + "',GV.LAST_LOGIN = SYSDATE where GV.ACCOUNT='"+account+"'");
                ResultSet resultSet = statement.executeQuery("select * from  SV join GV on GV.LOP=SV.LOP where GV.ACCOUNT=" + account);
                while (resultSet.next()) {
                    lstSV.add(new SV(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
                }
                System.out.println("\t\t\t\t\t\t\tDANH SÁCH SINH VIÊN :");
                System.out.println("\t\t\t\t---------------------------------------------------------");
                System.out.println("\t\t\t\tID " + "\t\t\tHọ và tên" + "\t\t\tEmail" + "\t\t\tSố điện thoai" + "\t\t\tGhi chú" + "\t\t\tLớp");
                for (SV sv : lstSV) {
                    System.out.println("\t\t\t\t" + sv.id + "\t\t\t" + sv.hoTen + "\t\t" + sv.email + "\t\t" + sv.sdt + "\t\t\t\t" + sv.ghiChu + "\t\t\t" + sv.lop);
                }
                Options();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            String string=in.nextLine();
            System.out.println("account không tồn tại");
//            System.out.println("Bạn có muốn tiếp tục: Y/N?");
//            switch (string){
//                case "Y": case"y":
//                    logIn();
//                    break;
//                default: System.exit(0);
//
//            }
        }

    }

    public void addNewSV() {
        Scanner input = new Scanner(System.in);
        //Connect connect=new Connect();
        Connection connection = Connect.getConnect();
        System.out.println("Nhập thông tin sinh viên cần thêm: ");
        System.out.print("Họ tên: ");
        String ht = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("SĐT: ");
        String sdt = input.nextLine();
        System.out.print("ghi chú: ");
        String note = input.nextLine();
        System.out.print("Lớp: ");
        String lop = input.nextLine();
        String sql="insert into SV " +
                "values(SV_SEQ.nextval,N'" + ht + "','" + email + "','" + sdt + "','N" + note + "','" + lop + "')";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm mới thành công!");
            }else System.out.println("thêm mới thất bại!");
            //Statement statement = connection.createStatement();
//           ResultSet resultSet= statement.executeQuery("insert into SV " +
//                    "values(SV_SEQ.nextval,'" + ht + "','" + email + "','" + sdt + "','" + note + "','" + lop + "')");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateSV(int id){
        Scanner input = new Scanner(System.in);
        Connection connection = Connect.getConnect();
        try {
            System.out.println("Nhập họ tên mới");
            String ht=input.nextLine();
            System.out.println("Nhập email mới: ");
            String email=input.nextLine();
            System.out.println("Nhập sđt: ");
            String sdt=input.nextLine();
            System.out.println("Ghi chú: ");
            String note=input.nextLine();
            System.out.println("Lớp");
            String lop=input.nextLine();
            String sql="update SV set HO_TEN=N'"+ht+"', EMAIL='"+email+"', SDT='"+sdt+
                    "', NOTE='N"+note+"', LOP='N"+lop+"' where ID="+id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Cập nhật thành công");
//            int rs=statement.executeUpdate(sql);
//            if(rs>0){
//                System.out.println("Cập nhật thành công");
//            }else System.out.println("Không có học viên có id=" +id);
            connection.close();
        } catch (Exception e) {
            System.out.println("Cập nhật thất bại");
            e.printStackTrace();
        }
    }

    public void deleteSV(int id){
        String sql="delete from SV where ID="+id;
        Connection connection = Connect.getConnect();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            //Statement statement = connection.createStatement();
            int rs=statement.executeUpdate();
            if(rs>0){
                System.out.println("Xóa thành công");
            }else System.out.println("Không có học viên có id=" +id);
        connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SearchSVByClass(String clazz) {
        Connection connection = Connect.getConnect();
        List<SV> lstSV = new ArrayList<SV>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from SV where SV.LOP='" + clazz + "'");
            while (resultSet.next()) {
                lstSV.add(new SV(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
            for (SV sv : lstSV) {
                System.out.println(sv.toString());
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SearchSVByHoTen(String ht) {
        Connection connection = Connect.getConnect();
        List<SV> lstSV = new ArrayList<SV>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from SV where SV.HO_TEN='" + ht + "'");
            while (resultSet.next()) {
                lstSV.add(new SV(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
            System.out.println("Danh sách học viên tên "+ht);
            for (SV sv : lstSV) {
                System.out.println(sv.toString());
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

