<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://media.istockphoto.com/id/1453165336/photo/contact-us-message-on-cube-blocks-on-wood-table.webp?b=1&s=170667a&w=0&k=20&c=GKx_g65-OYapZ5Z0FPxz_VxVyNQBYJgYZbH7Hp7zupQ='); /* Replace with your background image path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        h1 {
            color: black;
        }
        h2 {
            color: black;
        }
        h3 {
            color: black;
        }

        p {
            line-height: 1.6;
            color: black;
        }

        .contact-info {
            margin-top: 30px;
        }

        .social-media {
            margin-top: 20px;
        }

        .social-media a {
            margin-right: 10px;
            text-decoration: none;
        }
        .card {
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <header>
        <h1>Contact Us</h1>
    </header>
    <div class="card">
        <section class="contact-info">
            <h2>Contact Information</h2>
            <p>Email: <a href="mailto:shaikyasirahmed07@gmail.com">lelobankAP@gmail.com</a></p>
            <p>Phone: +91 9390902587</p>

            <h3>Physical Address</h3>
            <p>
                Lelo Bank<br>
                Guntur, 522001<br>
                Andhra Pradesh,INDIA <br>
            </p>
        </section>

        <section class="social-media">
            <h2>Connect with Us</h2>
            <a href="https://www.instagram.com/" target="_blank">Instagram</a>
            <a href="https://www.linkedin.com/in" target="_blank">LinkedIn</a>
            <!-- Add more social media links as needed -->
        </section>
    </div>

    <!-- Add a contact form if applicable -->

    <footer>
        <p>For inquiries, please use the contact details above.</p>
        <p>© Copyright 2024 by lelo bank. All rights reserved.</p>
    </footer>
</body>
</html>
