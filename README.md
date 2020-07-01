# hotrss

Read RSS feeds and extract "hot topics". REST API only.

POST /analysis: provide feedUrl parameters with URLs of the feeds that should be analyzed, returns integer id.
GET /frequency/{id}: get analysis by id.

