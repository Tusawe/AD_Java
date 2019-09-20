public abstract class Usuario {

    private String userName;
    private String password;

    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Usuario() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
