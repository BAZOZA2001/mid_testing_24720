<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm User Deletion</title>
    <style>
        /* General Body Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Modal Styling */
        .modal-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        h1 {
            color: #333;
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        p {
            font-size: 1em;
            color: #555;
            margin-bottom: 30px;
        }

        /* Button Styling */
        .button-container {
            display: flex;
            justify-content: space-between;
        }

        button, .cancel-link {
            font-size: 1em;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            cursor: pointer;
        }

        .delete-button {
            background-color: #dc3545;
            color: white;
        }

        .delete-button:hover {
            background-color: #c82333;
        }

        .cancel-link {
            background-color: #6c757d;
            color: white;
            text-align: center;
            display: inline-block;
        }

        .cancel-link:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="modal-container">
        <h1>Confirm User Deletion</h1>
        <p>Are you sure you want to delete this user? This action cannot be undone.</p>

        <form action="delete-user" method="POST">
            <input type="hidden" name="id" value="${user.personId}">
            <div class="button-container">
                <button type="submit" class="delete-button">Delete</button>
                <a href="admin/manage-users" class="cancel-link">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
