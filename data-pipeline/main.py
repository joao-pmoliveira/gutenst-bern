import argparse
from ast import parse
import json
import logging
import tarfile
from typing import Dict, List
from rdflib import Graph, Namespace, RDF

logging.basicConfig(level=logging.INFO, format='[%(levelname)s] %(message)s')

DCTERMS = Namespace("http://purl.org/dc/terms/")
PGTERMS = Namespace("http://www.gutenberg.org/2009/pgterms/")
RDF     = Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")

DEFAULT_TARFILE_PATH = "rdf-files.tar"
DEFAULT_OUTPUT_PATH = "pg-catalog.json"
DEFAULT_MAX_BOOKS = 400

def parse_book_metadata(g: Graph):
    for ebook in g.subjects(RDF.type, PGTERMS.ebook):
        return {
                "title": get_single(g, ebook, DCTERMS.title),
                "issued_at": get_single(g, ebook, DCTERMS.issued),
                "downloads": get_single(g, ebook, PGTERMS.downloads),
                "publisher": get_single(g, ebook, DCTERMS.publisher),
                "rights": get_single(g, ebook, DCTERMS. rights),
                "description": get_joined(g, ebook, DCTERMS.description),
                "language": get_list(g, ebook, DCTERMS.language),
                "subjects": get_list(g, ebook, DCTERMS.subject),
                "authors": get_authors(g, ebook),
                "files": get_files(g, ebook),
            }
    
def get_single(g: Graph, subject, predicate) -> str:
    return str(next(g.objects(subject, predicate), None))

def get_joined(g: Graph, subject, predicate) -> str:
    return " ".join(str(obj) for obj in g.objects(subject, predicate))

def get_list(g: Graph, subject, predicate) -> List[str]:
    return [get_single(g, subj_node, RDF.value) for subj_node in g.objects(subject, predicate)]

def get_authors(g: Graph, subject) -> List[Dict]:
    return [{
        "name": get_single(g, creator, PGTERMS.name),
        "birth": get_single(g, creator, PGTERMS.birthdate),
        "death": get_single(g, creator, PGTERMS.deathdate),
        "aliases": get_list(g, creator, PGTERMS.alias),
        "wiki": get_single(g, creator, PGTERMS.webpage),
        } for creator in g.objects(subject, DCTERMS.creator)]

def get_files(g: Graph, subject) -> List[Dict]:
    return [{
        "url": str(file_node),
        "format": [str(value)
            for fmt in g.objects(file_node, DCTERMS['format'])
            for value in g.objects(fmt, RDF.value)],
        } for file_node in g.objects(subject, DCTERMS.hasFormat)]

def parse_args():
    parser = argparse.ArgumentParser(description="Extract RDF book metadata into JSON file")
    parser.add_argument(
        "--input", "-i",
        default=DEFAULT_TARFILE_PATH,
        help="Path to RDF .tar archive"
    )
    parser.add_argument(
        "--output", "-o",
        default=DEFAULT_OUTPUT_PATH,
        help="Path to output JSON file"
    )
    parser.add_argument(
        "--limit", "-l", type=int,
        help="Max amount of books to parse"
    )

    return parser.parse_args()

if __name__ == "__main__":
    args = parse_args()
    logging.info("Starting RDF processing...")
    count = 0

    books = []
    with tarfile.open(args.input, "r") as tar:
        for member in tar:
            f = tar.extractfile(member)
            if not f or not member.name.endswith(".rdf"):
                continue
            
            count += 1
            try:
                g = Graph()
                g.parse(f, format="xml")
                book = parse_book_metadata(g)

                if book:
                    books.append(book)
                    logging.info(f"{count}: {book.get('title', 'Unknown Title')}")
            except Exception as e:
                logging.warning(f"Failed to parse {member.name}: {e}")

            if args.limit and args.limit >= 0 and count >= args.limit:
                break

    
    with open(args.output, "w", encoding="utf-8") as f:
        json.dump(books, f, indent=4)
    logging.info(f"Wrote {len(books)} books to {args.output}")
