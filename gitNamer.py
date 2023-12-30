import subprocess

def set_git_config(name, email):
    # Set user name
    subprocess.run(['git', 'config', 'user.name', name])

    # Set user email
    subprocess.run(['git', 'config', 'user.email', email])

if __name__ == "__main__":
    # Replace 'Your Name' and 'your.email@example.com' with your desired name and email
    set_git_config(input("Your Name: "), input("Your Email: "))

    print("Git configuration updated successfully.")