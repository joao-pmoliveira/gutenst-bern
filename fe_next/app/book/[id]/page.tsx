import { mapBookApi } from "@/lib/BookMapping";
import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";

async function getBook(id: number): Promise<any> {

    const res = await fetch(`http://localhost:8080/api/editions/${id}`);

    if (!res.ok) throw new Error("Failed to load book");

    const editionData = await res.json();

    const book: Book = {
        id: editionData["id"],
        bookId: editionData["bookId"],
        title: editionData["title"],
        originalTitle: "",
        description: editionData["description"],
        link: editionData["link"],
        cover: editionData["cover"],
        year: editionData["publicationYear"],
        pages: editionData["pages"],
        language: {
            id: editionData["language"]["id"],
            name: editionData["language"]["name"],
            code: editionData["language"]["code"],
        },
        authors: [],
        contributors: editionData["contributors"].map((c: any) => ({ id: c["authorId"], name: c["name"], role: c["role"] })),
    }

    const bookRes = await fetch(`http://localhost:8080/api/books/${book.bookId}`);
    const bookData = await bookRes.json();
    console.log(editionData);
    console.log(bookData);
    book.originalTitle = bookData["originalTitle"]
    book.authors = bookData["authors"].map((author: any) => ({ id: author["id"], name: author["name"] }));


    return book;
}

export default async function BookPage({ params }: { params: Promise<{ id: string }> }) {

    const { id } = await params;
    const book = await getBook(Number.parseInt(id));
    const defaultCover = "/next.svg";

    return (
        <main>
            <div className="
                flex
            ">
                <img
                    src={book.cover || defaultCover}
                    alt={"Cover of: " + (book.title || "N/A")}
                    className="
                        aspect-square
                        max-w-80
                        w-full rounded-md
                        object-contain
                    "
                />
                <div>
                    <h1 className="
                        text-3xl font-serif font-bold
                    ">
                        {book.title || "N/A"}
                    </h1>

                    <p className="
                        text-lg font-sans text-gray-500
                    ">
                        {
                            [
                                book.authors?.map((author: any) => author.name).join(", "),
                                book.contributors?.map((contr: any) => `${contr.name} (${contr.role})`).join(", ")
                            ].filter(Boolean)
                                .join(", ")
                        }
                    </p>

                    <p className=""> {book.description || "N/A"} </p>
                    <p className="">Published: {book.year || "N/A"} </p>
                    <p className="">Pages: {book.pages || "N/A"} </p>
                    <p className="">Language: {book.language.name || "N/A"} </p>
                    <a href={book.link || ""}>Visit Project Gutenberg</a>
                </div>
            </div>
        </main>
    )
}
