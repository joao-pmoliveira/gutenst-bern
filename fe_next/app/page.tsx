import '@/app/globals.css';
import BookCard from "@/components/BookCard";
import SearchBar from '@/components/SearchBar';
import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";

async function getBooks(): Promise<Book[]> {
    const res = await fetch('http://localhost:8080/api/books', {
        next: { revalidate: 0 },
    });

    if (!res.ok) throw new Error("Failed to fetch books");

    const data: BookApi[] = await res.json();

    return data.map(b => ({
        id: String(b.editionId),
        bookId: String(b.bookId),
        title: b.editionTitle,
        originalTitle: b.originalTitle,
        description: b.description,
        year: b.publicationYear,
        pages: String(b.pages),
        link: b.link,
        cover: b.cover,
        language: {
            name: b.languageName,
            code: b.languageCode
        },
        authors: b.authors.map(author => ({
            id: String(author.id),
            name: author.name,
        })),
        contributors: b.contributors.map(contributor => ({
            id: String(contributor.id),
            name: contributor.name,
            role: contributor.role
        })),
    }));

}

export default async function Home() {

    const books: Book[] = await getBooks();

    return (
        <>
            <header className="py-3 px-2 mb-8 bg-slate-300">
                <nav className="
                    flex items-center justify-between
                ">
                    <a className="basis-1/3">Project Gutenberg</a>

                    <SearchBar />

                    <div className="basis-1/3">
                        <p className="text-end">Navigation</p>
                    </div>
                </nav>
            </header>
            <main className="">
                <div className="
                    w-fit m-auto
                    grid grid-cols-3
                    gap-8
                ">
                    {
                        books
                            .map(book => <BookCard book={book} key={book.id} />)
                    }
                </div>
            </main>
        </>
    );
}
