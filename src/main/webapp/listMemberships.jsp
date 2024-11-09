<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Memberships</title>
    <style>
        /* General Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            font-size: 2em;
            margin-bottom: 30px;
        }

        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            background-color: #0056b3;
            color: white;
        }

        .table-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 900px;
            margin: 0 auto;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .actions a {
            margin-right: 10px;
        }

        .actions a:last-child {
            margin-right: 0;
        }

        .btn-add {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1em;
            text-align: center;
        }

        .btn-add:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

    <h2>List of Memberships</h2>

    <a href="addMembership.jsp?userId=${param.userId}" class="btn-add">Add Membership</a>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="membership" items="${memberships}">
                    <tr>
                        <td>${membership.type}</td>
                        <td>${membership.description}</td>
                        <td class="actions">
                            <a href="editMembership.jsp?membershipId=${membership.membershipId}&userId=${param.userId}">Edit</a>
                            <a href="membership?action=delete&membershipId=${membership.membershipId}&userId=${param.userId}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
