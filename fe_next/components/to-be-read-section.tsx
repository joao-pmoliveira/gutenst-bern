import { Book } from "@/types/Book";
import { BookApi } from "@/types/Api";
import { mapBookApi } from "@/lib/BookMapping";
import BookCardCompact from "./book/BookCardCompact";
import { Bookmark } from "lucide-react";

export default async function ToBeRead({ className = "" }) {
    const books = await getBooks();
    return (
        <section className={`${className} ml-8`}>
            <h1 className="text-2xl mb-6 font-bold flex items-center gap-2">
                <Bookmark size={24} strokeWidth={2} />
                To Be Read
            </h1>

            <div className="grid grid-cols-5 overflow-y-auto
                gap-2 gap-y-8">
                {books.slice(0, 10).map(book => <BookCardCompact key={book.id} book={book} />)}
            </div>
        </section>
    )
}

async function getBooks(): Promise<Book[]> {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/catalog`, {
        next: { revalidate: 0 }
    });

    if (!res.ok) throw new Error("Failed to fetch books");

    const data: BookApi[] = await res.json();
    return data.slice(0, 10).map(book => mapBookApi(book));
}
