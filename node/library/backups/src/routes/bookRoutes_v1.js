var express = require('express');
const bookRouter = express.Router();

var books = [
    {
        title: 'to be announced',
        genre: 'whatever',
        author: 'byme',
        read: false
    },
    {
        title: 'my fav book',
        genre: 'sportyread',
        author: 'againbyme',
        read: true
    }
];

bookRouter.route('/')
    .get((req, res) => {
        res.render('bookListView', {
            nav: [{ link: '/books', title: 'Books' },
            { link: '/authors', title: 'Authors' }],
            title: 'Library',
            books: books
        });
    });

bookRouter.route('/:id')
    .get((req, res) => {
        //const id = req.params.id;
        //can be done in es6 as below
         const { id } = req.params;
        res.render('bookView', {
            nav: [{ link: '/books', title: 'Books' },
            { link: '/authors', title: 'Authors' }],
            title: 'Library',
            book: books[id]
        });
    });

module.exports = bookRouter;