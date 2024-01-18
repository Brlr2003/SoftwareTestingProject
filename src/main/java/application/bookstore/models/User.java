package application.bookstore.models;


import java.io.*;



import java.util.ArrayList;



public class User extends BaseModel implements Serializable {
    private static final ArrayList<User> users = new ArrayList<>();
    private String username;
    private String password;
    private Role role;
    public static final String FILE_PATH = "data/users.ser";
    private static final File DATA_FILE = new File(FILE_PATH);
    @Serial
    private static final long serialVersionUID = 1234567L;
    public User() {}

    
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    
    public boolean ifusernameExists(){
        for(User u: users){
            if(u.getUsername().equals(this.getUsername()))
                return true;
        }
        return false;
    }

    public static ArrayList<User> getSearchResults(String searchText) {
        ArrayList<User> searchResults = new ArrayList<>();
        for(User User: getUsers())
            if (User.getUsername().toLowerCase().matches(".*"+searchText.toLowerCase()+".*"))
                searchResults.add(User);
        return searchResults;
    }
    
    public static User getUser(User potentialUser) {
        for(User user: getUsers())
            if (user.equals(potentialUser))
                return user;
        return null;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }


    @Override
    public String toString() {
        return "User{" +
                "username=" + getUsername() +
                ", password=" + getPassword() +
                ", role=" + getRole() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return other.getUsername().equals(getUsername()) && other.getPassword().equals(getPassword());
        }
        return false;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public static User getIfExists(User potentialUser) {
        for(User user: getUsers())
            if (user.equals(potentialUser))
                return user;
        return null;
    }
    public static ArrayList<User> getUsers () {
        if (users.size()==0){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    User temp = (User) inputStream.readObject();
                    if (temp == null)
                        break;
                    users.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of users file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public boolean saveInFile() {
        boolean saved = super.save(User.DATA_FILE);
        if (saved)
            users.add(this);
        return saved;
    }

    @Override
    public boolean isValid() {
    	 if (username.matches("\\w+")==false || password.matches("\\w+")==false)
             return false;
         return true;
    }

    @Override
    public boolean deleteFromFile() {
       users.remove(this);
        try {
            overwriteCurrentListToFile(DATA_FILE,users);
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public static<User extends BaseModel> void overwriteCurrentListToFile(File DATA_FILE, ArrayList<User> users) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE, false);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        if (users.size() == 0) {
        } else {
            for (User user : users)
                outputStream.writeObject(user);
        }
    }


	@Override
	public boolean updateFile() {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}