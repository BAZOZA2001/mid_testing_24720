<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        /* General Body Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            height: 100vh;
        }

        /* Header Styling */
        h1 {
            color: #333;
            font-size: 2.5em;
            text-align: center;
            margin: 20px 0;
        }

        h2 {
            color: #555;
            font-size: 1.8em;
            margin-top: 20px;
            font-weight: normal;
        }

        /* Dashboard Section */
        .dashboard-section {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin-bottom: 20px;
            font-size: 1.1em;
        }

        /* List Styling */
        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 12px 0;
            padding: 10px;
            background-color: #f9f9f9;
            border-left: 5px solid #007bff;
            font-size: 1.1em;
            border-radius: 4px;
        }

        li:nth-child(odd) {
            background-color: #f1f1f1;
        }

        /* Logout Button Styling */
        .logout-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
            font-size: 1.1em;
            text-decoration: none;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #0056b3;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            body {
                padding: 20px;
            }

            .dashboard-section {
                width: 100%;
                padding: 15px;
            }

            h1 {
                font-size: 2em;
            }

            h2 {
                font-size: 1.5em;
            }

            .logout-btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <h1>Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName}</h1>
    
    <!-- Borrowings Section -->
    <div class="dashboard-section">
        <h2>Your Borrowings:</h2>
        <ul>
            <c:forEach var="borrowing" items="${borrowings}">
                <li>${borrowing.book.title} - Status: ${borrowing.status}</li>
            </c:forEach>
        </ul>
    </div>

    <!-- Memberships Section -->
    <div class="dashboard-section">
        <h2>Your Memberships:</h2>
        <ul>
            <c:forEach var="membership" items="${memberships}">
                <li>${membership.type} - Expiry: ${membership.expiryDate}</li>
            </c:forEach>
        </ul>
    </div>

    <!-- Logout Button -->
    <a href="logout" class="logout-btn">Logout</a>

</body>
</html>
