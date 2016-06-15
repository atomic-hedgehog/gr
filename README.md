# gr

An application to display normalized favortie-color records.

## Installation

Clone this repo

## Usage

The easiest way to run the app is with Leiningen:

    $ lein run  [data files]

gr will print out views of all of the data from the data files combined and normalized.  In addition, a REST server will be started that allows the data to be viewed or new records to be added.


### Adding data with curl
The following curl command can be used to add records from the CLI:
`curl --data "last-name=Doe&first-name=Eve&gender=female&favorite-color=red&date-of-birth=1980-10-10" http://localhost:3333/records`

## Options

gr accepts any number of data files in one of the three formats: space-delimited, comma-delimited, or pipe-delimited.  There are example data files in `resources/examples`

## Examples

    $ lein run  resources/examples/comma_delimited


### Things I didn't have time for

* Better tests
* More function docs
* command line arg parsing to control the rest server and views
