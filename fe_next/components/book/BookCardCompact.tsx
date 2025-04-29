import { Book } from "@/types/Book";
import Link from "next/link";

export default function BookCardCompact(props: { book: Book }) {
    const book = props.book;
    return (
        <Link href={`/book/${book.id}`}
            className="w-full max-w-40
            group relative cursor-pointer
        ">
            <div className="aspect-square flex justify-center mb-2
                group-hover:bg-slate-200 group-hover:p-1" >
                <img alt={`Cover of ${book.title}`} src={book.cover}
                    className="h-full shadow-lg" />
            </div>

            <h3 className="text-md font-bold line-clamp-2">
                {book.title}</h3>

            <p className=" text-xs font-sans
                text-gray-900 font-light
            ">
                {book.authors.map(author => author.name).join("; ")}
            </p>
        </Link>)
}
