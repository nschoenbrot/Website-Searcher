# Website-Searcher

## Requirements

Given a list of urls in urls.txt, fetch each page and determine whether a search term exists on the page (this search can be a really rudimentary regex - this part isn't too important).

You can make up the search terms. Ignore the addition information in the urls.txt file.

Constraints
* Search is case insensitive
* Should be concurrent.
* It should not have more than 20 HTTP requests at any given time.
* The results should be written out to results.txt
* Avoid using thread pooling libraries like Executor, ThreadPoolExecutor, Celluloid, or Parallel streams.

urls.txt: https://s3.amazonaws.com/fieldlens-public/urls.txt

The solution must be able to be run from the command line (don't assume JDK is available).

## Instructions to run

<p>An executable jar is pre-built and committed (built using `mvn clean package`).</p>
To execute ("keyword" can be any word): <p>`java -jar target/Website-Searcher-jar-with-dependencies.jar keyword`</p>
<p>Results are outputed to results.txt in the current directory. If a timout or exception occurs while visting a paticular url, the result will be treated as if the keyword was not found on the page.</p>