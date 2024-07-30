<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: white;
        }

        .container {
            text-align: center;
            background: #333;
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            width: 300px;
            color: white;
        }

        h1 {
            margin-bottom: 1rem;
            font-size: 1.2rem;
            color: #ccc;
        }

        .star-rating {
            direction: rtl;
            display: inline-block;
            font-size: 2rem;
            margin-bottom: 1rem;
        }

        .star-rating input {
            display: none;
        }

        .star-rating label {
            color: white;
            cursor: pointer;
            transition: color 0.3s;
            padding: 0 0.1rem;
        }

        .star-rating input:checked ~ label,
        .star-rating label:hover,
        .star-rating label:hover ~ label {
            color: #4caf50;
        }

        textarea {
            width: 100%;
            height: 100px;
            padding: 0.5rem;
            border-radius: 5px;
            border: none;
            margin-bottom: 1rem;
            resize: none;
            font-family: inherit;
            font-size: 1rem;
            background: #555;
            color: white;
        }

        textarea::placeholder {
            color: #ccc;
        }

        button {
            background: #4caf50;
            color: white;
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 25px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background: white;
            color: #4caf50;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Rate your experience</h1>
        <form action="index.jsp" method="post">
            <div class="star-rating">
                <input type="radio" id="star5" name="rating" value="5"><label for="star5" title="5 stars">&#9733;</label>
                <input type="radio" id="star4" name="rating" value="4"><label for="star4" title="4 stars">&#9733;</label>
                <input type="radio" id="star3" name="rating" value="3"><label for="star3" title="3 stars">&#9733;</label>
                <input type="radio" id="star2" name="rating" value="2"><label for="star2" title="2 stars">&#9733;</label>
                <input type="radio" id="star1" name="rating" value="1"><label for="star1" title="1 star">&#9733;</label>
            </div>
            <br>
            <button type="submit">Send</button>
        </form>
    </div>
</body>
</html>
