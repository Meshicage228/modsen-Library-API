--liquibase formatted sql

--changeset meshicage:2

INSERT INTO genres (name)
VALUES ('Fiction'),
       ('Non-Fiction'),
       ('Science Fiction'),
       ('Fantasy'),
       ('Mystery'),
       ('Biography'),
       ('Historical Fiction'),
       ('Romance'),
       ('Thriller'),
       ( 'Self-Help');

INSERT INTO books (isbn, title, genre_id, description, author, created_at, updated_at)
VALUES ('978-3-16-148410-0', 'The Great Gatsby', 1,
        'A novel set in the Roaring Twenties that tells the story of Jay Gatsby and his unrequited love for Daisy Buchanan.',
        'F. Scott Fitzgerald', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-06-112008-4', 'To Kill a Mockingbird', 1,
        'A novel about the serious issues of rape and racial inequality, narrated by a young girl named Scout Finch.',
        'Harper Lee', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-7432-7356-5', 'Sapiens: A Brief History of Humankind', 2,
        'An exploration of the history of humankind from the Stone Age to the modern age.', 'Yuval Noah Harari',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-452-28423-4', 'Dune', 3,
        'A science fiction novel set in a distant future amidst a huge interstellar empire, focusing on the young Paul Atreides.',
        'Frank Herbert', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-345-39180-3', 'The Hobbit', 4,
        'A fantasy novel that follows the journey of Bilbo Baggins, a hobbit who embarks on an adventure with a group of dwarves.',
        'J.R.R. Tolkien', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-06-230167-3', 'The Girl with the Dragon Tattoo', 5,
        'A mystery novel that follows journalist Mikael Blomkvist and hacker Lisbeth Salander as they investigate a decades-old disappearance.',
        'Stieg Larsson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-06-245771-4', 'Becoming', 6,
        'A memoir by former First Lady Michelle Obama, detailing her life from childhood to her years in the White House.',
        'Michelle Obama', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-7532-7356-5', 'The Nightingale', 7,
        'A historical fiction novel about two sisters in France during World War II and their struggle to survive and resist the German occupation.',
        'Kristin Hannah', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-06-231500-7', 'Pride and Prejudice', 8,
        'A romantic novel that follows the life of Elizabeth Bennet as she navigates issues of class, marriage, and morality.',
        'Jane Austen', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('978-0-06-256313-4', 'The Silent Patient', 9,
        'A psychological thriller about a woman who shoots her husband and then stops speaking, and the psychotherapist who tries to uncover her motive.',
        'Alex Michaelides', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);