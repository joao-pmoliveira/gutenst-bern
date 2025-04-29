import { mapBookApi } from "@/lib/BookMapping";
import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";
import { History } from "lucide-react";
import BookCardCompact from "./book/BookCardCompact";

export default async function RecentHistory({ className = "" }) {
    const books = await getBooks();
    return (
        <div className={`${className} overflow-x-hidden mr-8`}>
            <h1 className="text-2xl mb-6 font-bold flex items-center gap-2">
                <History size={24} strokeWidth={2} />
                History
            </h1>
            <div className="flex flex-nowrap pb-4 *:min-w-30 space-x-6 overflow-x-auto">
                {books.map(book =>
                    <BookCardCompact key={book.id} book={book} />)
                }
            </div>
        </div>
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
