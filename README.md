# hotrss

Read RSS feeds and extract "hot topics". REST API only.

API endpoints:

/analysis (POST): provide feedUrl parameters with URLs of the feeds that should be analyzed, returns integer id.

/frequency/{id} (GET): get analysis by id.
