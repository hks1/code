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
            (async function query() {
                const request = new sql.Request();

                const result = await request.query('select * from books');

                //debug(result);
                res.render('bookListView', {
                    nav,
                    title: 'Library',
                    books: result.recordset
                });

            }())


        });

    bookRouter.route('/:id')
    .all((req, res, next) => {
        (async function query() {
            //const id = req.params.id;
            //can be done in es6 as below - object destructuring
            const { id } = req.params;
            const request = new sql.Request();

            const {recordset} =
                await request.input('id', sql.Int, id)
                    .query('select * from books where id = @id');
            //req.book = recordset[0];
            // array destructuring
            [req.book] = recordset;
            next();
            
        }())   
    })
        .get((req, res) => {
            res.render('bookView', {
                nav,
                title: 'Library',
                book: req.book
            });
            
        });
    return bookRouter;
}
//module.exports = bookRouter;
module.exports = router;