var express = require('express');
const bookRouter = express.Router();
const sql = require('mssql')
const debug = require('debug')('app:bookRoutes')

function router(nav) {
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
            (async function query(){
                const request = new sql.Request();

                const result = await request.query('select * from books')
                   
                        debug(result);
                        res.render('bookListView', {
                            nav,
                            title: 'Library',
                            books: result.recordset
                        });
                    
            }())
            
            
        });

    bookRouter.route('/:id')
        .get((req, res) => {
            //const id = req.params.id;
            //can be done in es6 as below
            const { id } = req.params;
            res.render('bookView', {
                nav,
                title: 'Library',
                book: books[id]
            });
        });
    return bookRouter;
}
//module.exports = bookRouter;
module.exports = router;