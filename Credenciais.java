public class Credenciais {
    
    private String email;
    private String password;

    public Credenciais(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credenciais cred = (Credenciais) o;
        return this.email.equals(cred.email) && this.password.equals(cred.password);
    }

        /**
     * Método toString
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("     | Credenciais |\n");
        sb.append(" Email -> " + this.getEmail() + "\n");
        sb.append(" Password -> " + this.getPassword() + "\n");

        return sb.toString();
    }
}

