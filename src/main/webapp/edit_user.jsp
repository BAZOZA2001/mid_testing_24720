<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <style>
        /* General Body Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
        }

        /* Form Styling */
        .form-container {
            background-color: #fff;
            width: 100%;
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            font-size: 2em;
            margin-bottom: 30px;
        }

        label {
            display: block;
            font-size: 1em;
            margin-bottom: 8px;
            color: #333;
        }

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 2px solid #ddd;
            border-radius: 4px;
            font-size: 1em;
            box-sizing: border-box;
        }

        input[type="text"]:focus, select:focus {
            border-color: #007bff;
            outline: none;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-align: center;
            font-size: 1em;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .form-container {
                width: 100%;
                padding: 20px;
            }

            h1 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h1>Edit User</h1>
        <form action="edit-user" method="POST">
            <input type="hidden" name="id" value="${user.personId}">

            <label for="firstName">First Name</label>
            <input type="text" name="firstName" value="${user.firstName}" required>

            <label for="lastName">Last Name</label>
            <input type="text" name="lastName" value="${user.lastName}" required>

            <label for="userName">Username</label>
            <input type="text" name="userName" value="${user.userName}" required>

            <label for="role">Role</label>
            <select name="role" required>
                <option value="TEACHER" ${user.role == 'TEACHER' ? 'selected' : ''}>Teacher</option>
                <option value="STUDENT" ${user.role == 'STUDENT' ? 'selected' : ''}>Student</option>
                <option value="MANAGER" ${user.role == 'MANAGER' ? 'selected' : ''}>Manager</option>
            </select>

            <input type="submit" value="Update User">
        </form>

        <a href="admin/manage-users" class="back-link">Back to User Management</a>
    </div>

</body>
</html>
